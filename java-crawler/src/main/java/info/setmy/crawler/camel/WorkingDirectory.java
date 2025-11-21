package info.setmy.crawler.camel;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.File;

@Getter
@SuperBuilder(toBuilder = true)
public final class WorkingDirectory extends Directory {

    private static final String INPUT = "input";
    private static final String PROCESSING = "processing";
    private static final String PROCESSED = "processed";
    private static final String OUTPUT = "output";

    private File input;
    private File processing;
    private File processed;
    private File output;

    public WorkingDirectory(final File directory) {
        super(directory);
    }

    public WorkingDirectory init() {
        input = new File(directory, INPUT);
        processing = new File(directory, PROCESSING);
        processed = new File(directory, PROCESSED);
        output = new File(directory, OUTPUT);
        if (input.exists() && !input.isDirectory()) {
            throw new RuntimeException("Input directory exists and is not a directory");
        }
        if (processing.exists() && !processing.isDirectory()) {
            throw new RuntimeException("Processing directory exists and is not a directory");
        }
        if (processed.exists() && !processed.isDirectory()) {
            throw new RuntimeException("Processed directory exists and is not a directory");
        }
        if (output.exists() && !output.isDirectory()) {
            throw new RuntimeException("Output directory exists and is not a directory");
        }
        if (!input.exists()) {
            input.mkdirs();
        }
        if (!processing.exists()) {
            processing.mkdirs();
        }
        if (!processed.exists()) {
            processed.mkdirs();
        }
        if (!output.exists()) {
            output.mkdirs();
        }
        return this;
    }
}

