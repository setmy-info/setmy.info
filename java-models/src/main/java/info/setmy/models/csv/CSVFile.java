package info.setmy.models.csv;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class CSVFile<T> implements Closeable {

    @NonNull
    private final String fileName;

    @NonNull
    private final CSVConfig config;

    private final Reader reader;

    private final CSVParser parser;

    private final Iterator<CSVRecord> iterator;

    @NonNull
    private final Function<CSVRecord, T> paringFunction;

    private long skipped;

    public CSVFile<T> init() {
        try {
            var reader = new InputStreamReader(new FileInputStream(fileName), config.getCharsetName());
            var parser = CSVParser.parse(
                reader,
                CSVFormat.DEFAULT
                    .builder()
                    .setIgnoreEmptyLines(true)
                    .build()
                    .withDelimiter(config.getDelimiter())
                    .withQuote(config.getQuote())
                    //.withQuoteMode(QuoteMode.MINIMAL)
                    .withFirstRecordAsHeader());
            var result = this.toBuilder()
                .reader(reader)
                .parser(parser)
                .iterator(parser.iterator())
                .build();
            result.skip();
            return result;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        if (reader == null) {
            return;
        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<T> get() {
        if (!iterator.hasNext()) {
            return empty();
        }
        final CSVRecord record = iterator.next();
        final T result = paringFunction.apply(record);
        return ofNullable(result);
    }

    private void skip() {
        while (skipped < config.getSkip()) {
            if (iterator.hasNext()) {
                iterator.next();
            }
            skipped++;
        }
    }
}
