package info.setmy.crawler.camel;

import org.apache.camel.Exchange;

public class ExampleService implements Named {

    @Override
    public String getName() {
        return "exampleService";
    }

    public void enrich(Exchange exchange) {
        String input = exchange.getIn().getBody(String.class);
        exchange.getIn().setBody(input + " -> Enriched");
    }
}
