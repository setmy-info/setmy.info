package info.setmy.crawler.scraper.models;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.File;

@Getter
@SuperBuilder(toBuilder = true)
public final class WorkingDirectory extends Directory {

    private static final String INPUT = "input";
    private static final String PROCESSED = "processed";
    private static final String OUTPUT = "output";
    private static final String ERROR = "error";

    private File input;
    private File processed;
    private File output;
    private File error;

    public WorkingDirectory(final File directory) {
        super(directory);
    }

    public WorkingDirectory init() {
        input = create(validate(newFile(INPUT)));
        processed = create(validate(newFile(PROCESSED)));
        output = create(validate(newFile(OUTPUT)));
        error = create(validate(newFile(ERROR)));
        return this;
    }
}

