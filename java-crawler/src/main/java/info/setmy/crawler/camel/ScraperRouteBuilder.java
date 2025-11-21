package info.setmy.crawler.camel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ThreadsDefinition;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
@Log4j2
@RequiredArgsConstructor
public class ScraperRouteBuilder extends RouteBuilder {

    //"scrapingELT"
    private final ScraperRouteBuilderConfig scraperRouteBuilderConfig;

    private final List<CamelBean> camelBeanList = new ArrayList<>();

    public void addCamelBean(final CamelBean camelBean) {
        camelBeanList.add(camelBean);
    }

    @Override
    public void configure() {
        final File input = scraperRouteBuilderConfig.workingDirectory().getInput();
        final File processing = scraperRouteBuilderConfig.workingDirectory().getProcessing();
        final File processed = scraperRouteBuilderConfig.workingDirectory().getProcessed();

        final String inputDirectoryPath = "file://"
            + input.getAbsolutePath()
            + "?include=.*\\.csv"
            + "&move=" + processing.getAbsolutePath()
            + "/${file:name}"
            + "&delay=5000";

        from(inputDirectoryPath)
            .routeId("fileIntake")
            .log("Picked up: ${header.CamelFileName} → moved to processing/")
            .split()
            .tokenize("[\r\n]+")
            .streaming()
            .filter(simple("${body.trim().length} > 0"))
            .filter(simple("${body} not regex '^\\s*\"?name\"?;'"))
            .to("direct:start")
            .end()
            .log("All lines processed for: ${header.CamelFileName}")
            // TODO: here move to processed
            .log("Moved to processed: ${header.CamelFileName}");

        ThreadsDefinition threadsDefinition = from("direct:start")
            .routeId(scraperRouteBuilderConfig.routeId())
            .threads().executorService(scraperRouteBuilderConfig.executorPoolId())
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
