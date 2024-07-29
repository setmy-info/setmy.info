package info.setmy.csv;

import info.setmy.models.csv.CSVConfig;
import info.setmy.models.csv.CSVFile;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;
import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class CSVFileIT {

    @Test
    public void test() {
        final List<ParsedRecord> records = new ArrayList<>();
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
            Optional<ParsedRecord> row = csvFile.get();
            while (row.isPresent()) {
                log.info("Result: {}", row);
                row.ifPresent(parsedRecord -> records.add(parsedRecord));
                row = csvFile.get();
            }
        }
        assertThat(records).size().isEqualTo(3);
        assertThat(records.get(0).getNumber()).isEqualTo(new BigDecimal("123.456"));
        assertThat(records.get(0).getValue()).isEqualTo("ÕõÄäÖöÜü");
        assertThat(records.get(0).getLocalDateTime()).isEqualTo(LocalDateTime.parse("2024-12-31T23:59:57.678+02:00", ISO_OFFSET_DATE_TIME));
    }
}

