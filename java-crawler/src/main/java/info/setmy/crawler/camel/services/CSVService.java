package info.setmy.crawler.camel.services;

import info.setmy.crawler.camel.CamelBean;
import lombok.extern.log4j.Log4j2;
import org.apache.camel.Exchange;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;

@Log4j2
public class CSVService implements CamelBean {

    private final CSVFormat format = CSVFormat.DEFAULT
        .withDelimiter(';')
        .withQuote('"')
        .withHeader("name", "url")
        .withIgnoreSurroundingSpaces()
        .withTrim();

    @Override
    public String getName() {
        return "csvService";
    }

    @Override
    public void doRun(final Exchange exchange) {
        final String cswRow = exchange.getIn().getBody(String.class);
        try {
            final CSVParser parser = CSVParser.parse(cswRow, format);
            final CSVRecord record = parser.getRecords().get(0);
            final info.setmy.crawler.entities.CSVRecord csvRecord = map(record);
            exchange.getIn().setBody(csvRecord);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private info.setmy.crawler.entities.CSVRecord map(final CSVRecord record) {
        try {
            if (true) {
                throw new RuntimeException("asdgzsdg");
            }
            return info.setmy.crawler.entities.CSVRecord.builder()
                .name(record.get(0))
                .url(record.get(1))
                .build();
        } catch (java.lang.ArrayIndexOutOfBoundsException exception) {
            log.error("CSV row have error {}", record, exception);
            throw new RuntimeException(exception);
        }
    }
}
