package info.setmy.crawler.camel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.ThreadPoolProfile;

@Log4j2
@Getter
@RequiredArgsConstructor
public class Camel {

    private final CamelConfig camelConfig;

    private final CamelContext context = new DefaultCamelContext();

    public void bind(final CamelNamed named) {
        context.getRegistry().bind(named.getName(), named);
    }

    public void registerThreadPool(final CamelThreadsConfig threadsConfig) {
        registerThreadPoolProfile(toThreadPoolProfile(threadsConfig));
    }

    private ThreadPoolProfile toThreadPoolProfile(final CamelThreadsConfig threadsConfig) {
        final ThreadPoolProfile threadPoolProfile = new ThreadPoolProfile();
        threadPoolProfile.setId(threadsConfig.poolId());
        threadPoolProfile.setPoolSize(threadsConfig.poolSize());
        threadPoolProfile.setMaxPoolSize(threadsConfig.maxPoolSize());
        threadPoolProfile.setMaxQueueSize(threadsConfig.maxQueueSize());
        return threadPoolProfile;
    }

    private void registerThreadPoolProfile(ThreadPoolProfile threadPoolProfile) {
        context.getExecutorServiceManager().registerThreadPoolProfile(threadPoolProfile);
    }

    public void add(final RouteBuilder routeBuilder) {
        try {
            context.addRoutes(routeBuilder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        context.start();
        ProducerTemplate template = context.createProducerTemplate();
        for (int i = 0; i < 10; i++) {
            template.asyncSendBody("direct:start", "Begin " + i);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        context.stop();
    }
}
