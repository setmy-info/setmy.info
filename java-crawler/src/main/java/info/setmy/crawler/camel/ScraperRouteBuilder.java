package info.setmy.crawler.camel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ThreadsDefinition;

import java.util.ArrayList;
import java.util.List;

@Getter
@Log4j2
@RequiredArgsConstructor
public class ScraperRouteBuilder extends RouteBuilder {

    //"scrapingELT"
    private final String routeId;
    private final String executorPoolId;

    private final List<CamelBean> camelBeanList = new ArrayList<>();

    public void addCamelBean(final CamelBean camelBean) {
        camelBeanList.add(camelBean);
    }

    @Override
    public void configure() {
        ThreadsDefinition threadsDefinition = from("direct:start")
            .routeId(routeId)
            .threads().executorService(executorPoolId)
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
            });
        threadsDefinition = addBeansToBuilder(threadsDefinition);
        threadsDefinition.to("direct:end");

        from("direct:end")
            .routeId("finalizationRoute")
            .log("Processed message: ${body}");
    }

    private ThreadsDefinition addBeansToBuilder(final ThreadsDefinition threadsDefinition) {
        camelBeanList.forEach(camelBean -> threadsDefinition.log("Entering bean: " + camelBean.getName())
            .bean(camelBean.getName(), "doRun")
            .log("Done bean: " + camelBean.getName()));
        return threadsDefinition;
    }
}
