package info.setmy.crawler.camel;

import org.apache.camel.Exchange;

public interface CamelRunner {

    void doRun(Exchange exchange);
}
