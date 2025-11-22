package info.setmy.crawler.camel;

import info.setmy.crawler.entities.CSVRecord;
import info.setmy.crawler.entities.RecordEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.GenericFile;

import java.io.File;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Getter
@Log4j2
@RequiredArgsConstructor
public class ScraperRouteBuilder extends RouteBuilder {

    private final ScraperRouteBuilderConfig scraperRouteBuilderConfig;

    @Override
    public void configure() {
        final File inputFile = scraperRouteBuilderConfig.workingDirectory().getInput();
        final File errorFile = scraperRouteBuilderConfig.workingDirectory().getError();
        final File processedFile = scraperRouteBuilderConfig.workingDirectory().getProcessed();
        final String inputPath = inputFile.getAbsolutePath();
        final String errorPath = errorFile.getAbsolutePath();
        final String processedPath = processedFile.getAbsolutePath();

        //final String camelUri = "file://" + inputPath + "?include=.*\\.csv&noop=true&initialDelay=1000&delay=30000";
        /*final String camelUri = "file://" + inputPath +
            "?include=.*\\.csv&move=" + processedPath +
            "/${file:name}&moveFailed=" + inputPath +
            "/error/${file:name}&initialDelay=1000&delay=30000";*/
        final String camelUri = "file://" + inputPath +
            "?include=.*\\.csv&move=" + processedPath +
            "/${file:name}&moveFailed=" + errorPath + // Does not go to the error folder when SCV parsed has an exception
            "/${file:name}&initialDelay=1000&delay=30000";

        from(camelUri)
            .routeId("scraperCSVFileLogger")
            .log("========= FILE START =============")
            .to("direct:processScraperCSVFile")
            .log("========= FILE END   =============");

        from("direct:processScraperCSVFile")
            .routeId("processScraperCSVFile")
            .log("========= processScraperCSVFile START =============")
            .process(exchange -> {
                final Object body = exchange.getIn().getBody();
                final GenericFile<File> genericFile = (GenericFile) body;
                final String fileName = exchange.getIn().getHeader("CamelFileName", String.class);
                final String filePath = exchange.getIn().getHeader("CamelFileAbsolutePath", String.class);
                final String parentPath = exchange.getIn().getHeader("CamelFileParent", String.class);
                final Long fileSize = exchange.getIn().getHeader("CamelFileLength", Long.class);
                final Long modTime = exchange.getIn().getHeader("CamelFileLastModified", Long.class);
                log.info("Faili nimi: {}", fileName);
                log.info("Täispath: {}", filePath);
                log.info("Kausta path: {}", parentPath);
                log.info("Failisuurus: {} baiti", fileSize);
                log.info("Muutmise aeg: {} (timestamp: {})", formatTimestamp(modTime), modTime);
                log.info("Body: {}", genericFile);
            })
            .to("direct:splitScraperCSVFile")
            .log("========= processScraperCSVFile END =============");

        from("direct:splitScraperCSVFile")
            .routeId("splitScraperCSVFile")
            .log("========= splitScraperCSVFile: ${header.CamelFileName} START =============")
            .split(body().tokenize("\n"))
            .streaming()
            .stopOnException()
            .filter(simple("${body} != null && ${body.trim().length()} > 0"))
            .filter(simple("${body} not regex '^\\s*\"?name\"?\\s*;\\s*\"?url\"?\\s*$'"))// Header out
            .to("direct:cleanScraperCSVFileRow")
            .to("seda:parallelProcessingScraperCSVFileRows")
            .end()// Split end, back on file level
            .log("========= splitScraperCSVFile: ${header.CamelFileName} END =============");

        from("direct:cleanScraperCSVFileRow")
            .routeId("cleanScraperCSVFileRow")
            .log("========= cleanScraperCSVFileRow START =============")
            .process(exchange -> {
                final String row = exchange.getIn().getBody(String.class)
                    .trim();
                log.info("CSV rida: {}", row);
                exchange.getIn().setBody(row);
            })
            .log("========= cleanScraperCSVFileRow END =============");

        from("seda:parallelProcessingScraperCSVFileRows")
            .routeId("parallelProcessingScraperCSVFileRows")
            .log("========= parallelProcessingScraperCSVFileRows START =============")
            .threads().executorService(scraperRouteBuilderConfig.executorPoolId())
            .bean("csvService", "doRun")
            .log("Entering step 1")
            .process(exchange -> {
                final CSVRecord body = exchange.getIn().getBody(CSVRecord.class);
                final RecordEntity record = RecordEntity.builder()
                    .name(body.getName())
                    .url(body.getUrl())
                    .content(new HashMap<>())
                    .build();
                exchange.getIn().setBody(body + " -> Step 1");
            })
            .log("Entering step 2")
            .process(exchange -> {
                String body = exchange.getIn().getBody(String.class);
                exchange.getIn().setBody(body + " -> Step 2");
            })
            .log("========= parallelProcessingScraperCSVFileRows END =============");
    }

    private String formatTimestamp(Long timestamp) {
        if (timestamp == null)
            return "Määramata";
        return Instant.ofEpochMilli(timestamp)
            .atZone(ZoneId.systemDefault())
            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }
}
