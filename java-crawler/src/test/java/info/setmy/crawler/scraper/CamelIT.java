package info.setmy.crawler.scraper;

import info.setmy.crawler.scraper.tools.ScrapingCommand;
import info.setmy.crawler.scraper.tools.Tools;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.ThreadPoolProfile;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static info.setmy.crawler.scraper.tools.Tools.newTools;

class CamelIT {

    CamelContext context = new DefaultCamelContext();

    @Test
    void test() throws Exception {

        ThreadPoolProfile threadPoolProfile = new ThreadPoolProfile();
        threadPoolProfile.setId("scraperPool");
        threadPoolProfile.setPoolSize(5);
        threadPoolProfile.setMaxPoolSize(5);
        threadPoolProfile.setMaxQueueSize(0);

        context.getRegistry().bind("exampleService", new ExampleService());

        context.getExecutorServiceManager().registerThreadPoolProfile(threadPoolProfile);

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:start")
                    .routeId("mainRoute")
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
        });
        context.start();
        ProducerTemplate template = context.createProducerTemplate();
        for (int i = 0; i < 10; i++) {
            template.asyncSendBody("direct:start", "Begin " + i);
        }
        //context.createProducerTemplate().sendBody("direct:start", "Begin");
        Thread.sleep(2000);
        context.stop();
    }

    @Test
    @Disabled
    void test2() {
        final ScraperConfig scraperConfig = new ScraperConfig("localhost", 4444, false);
        final ScrapingCommand scrapingCommand = ScrapingCommand.builder()
            .source("")
            .destination("")
            .maxPoolSize(5)
            .poolSize(5)
            .scraperConfig(scraperConfig)
            .build();
        Tools tools = newTools(scrapingCommand);
        tools.execute();
    }

    public static class ExampleService {
        /*
        public String enrich(String input) {
            return input + " -> Enriched";
        }
        */
        public void enrich(Exchange exchange) {
            String input = exchange.getIn().getBody(String.class);
            exchange.getIn().setBody(input + " -> Enriched");
        }
    }
}
