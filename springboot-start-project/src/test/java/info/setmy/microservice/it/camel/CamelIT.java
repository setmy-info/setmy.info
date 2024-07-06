package info.setmy.microservice.it.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Test;

public class CamelIT {

    CamelContext context = new DefaultCamelContext();

    @Test
    public void test() throws Exception {

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:start")
                    .routeId("mainRoute")
                    .log("Entering step 1")
                    .process(exchange -> {
                        String body = exchange.getIn().getBody(String.class);
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
        context.createProducerTemplate().sendBody("direct:start", "Algus");
        Thread.sleep(2000);
        context.stop();
    }
}
