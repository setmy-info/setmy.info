package info.setmy.crawler.camel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.camel.builder.RouteBuilder;

@Getter
@Log4j2
@RequiredArgsConstructor
public class ScraperRouteBuilder extends RouteBuilder {

    //"scrapingELT"
    private final String routeId;
    private final String executorPoolId;

    @Override
    public void configure() throws Exception {
        from("direct:start")
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
            })
            .log("Entering step 3")
            .bean("exampleService", "enrich")
            .log("Done")
            .to("direct:end");

        from("direct:end")
            .routeId("finalizationRoute")
            .log("Processed message: ${body}");
    }
}
