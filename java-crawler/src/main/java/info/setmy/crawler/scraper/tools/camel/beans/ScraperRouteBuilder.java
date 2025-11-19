package info.setmy.crawler.scraper.tools.camel.beans;

import org.apache.camel.builder.RouteBuilder;

public class ScraperRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:start")
            .routeId("scrapingELT")
            .threads().executorService("scraperPool")
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
