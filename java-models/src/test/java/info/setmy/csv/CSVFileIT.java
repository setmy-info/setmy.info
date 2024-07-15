package info.setmy.csv;

import info.setmy.models.csv.CSVConfig;
import info.setmy.models.csv.CSVFile;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Function;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;

@Log4j2
public class CSVFileIT {

    @Test
    public void test() {
        final CSVConfig csvConfig = CSVConfig.builder()
            .charsetName("Windows-1252")
            .delimiter('t')
            .quote('"')
            .skip(1L)
            .build();
        final Function<CSVRecord, ParsedRecord> parsingFunction = (CSVRecord record) -> ParsedRecord.builder()
            .number(new BigDecimal(record.get("Aaa").replace(",", ".")))
            .localDateTime(LocalDateTime.parse(record.get("Bbb"), ISO_OFFSET_DATE_TIME))
            .value(record.get("Ccc"))
            .build();
        try (final var csvFile = CSVFile.<ParsedRecord>builder()
            .fileName("src/test/resources/example.csv")
            .config(csvConfig)
            .paringFunction(parsingFunction)
            .build()
            .init()) {
            Optional<ParsedRecord> result = csvFile.get();
            while (result.isPresent()) {
                log.info("Result: {}", result);
                result = csvFile.get();
            }
        }
    }
}

