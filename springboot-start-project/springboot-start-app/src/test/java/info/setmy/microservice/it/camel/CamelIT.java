package info.setmy.microservice.it.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.ThreadPoolProfile;
import org.junit.jupiter.api.Test;

class CamelIT {

    CamelContext context = new DefaultCamelContext();

    @Test
    void test() throws Exception {

        ThreadPoolProfile threadPoolProfile = new ThreadPoolProfile();
        threadPoolProfile.setId("myPool");
        threadPoolProfile.setPoolSize(5);
        threadPoolProfile.setMaxPoolSize(5);
        threadPoolProfile.setMaxQueueSize(0);

        context.getExecutorServiceManager().registerThreadPoolProfile(threadPoolProfile);

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:start")
                    .routeId("mainRoute")
                    .threads().executorService("myPool")
                    .log("Entering step 1")
                    .process(exchange -> {
                        String body = exchange.getIn().getBody(String.class);
                        //Thread.sleep(300);
                        exchange.getIn().setBody(body + " -> Step 1");
                    })
                    .log("Entering step 2")
                    .process(exchange -> {
                        String body = exchange.getIn().getBody(String.class);
                        exchange.getIn().setBody(body + " -> Step 2");
                    })
                    .log("Done")
                    .to("direct:end");

                from("direct:end")
                    .routeId("finalizationRoute")
                    .log("Processed message: ${body}");
            }
        });
        context.start();
        ProducerTemplate template = context.createProducerTemplate();
        for (int i = 0; i < 10; i++) {
            template.asyncSendBody("direct:start", "Algus " + i);
        }
        //context.createProducerTemplate().sendBody("direct:start", "Algus");
        Thread.sleep(2000);
        context.stop();
    }
}
