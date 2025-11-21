package info.setmy.crawler.camel;

import org.apache.camel.Exchange;

public class ExampleService implements CamelBean {

    @Override
    public String getName() {
        return "exampleService";
    }

    @Override
    public void doRun(final Exchange exchange) {
        String input = exchange.getIn().getBody(String.class);
        exchange.getIn().setBody(input + " -> Enriched");
    }
}
