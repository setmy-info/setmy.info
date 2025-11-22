package info.setmy.crawler.camel;

import lombok.experimental.SuperBuilder;

import java.io.File;

@SuperBuilder(toBuilder = true)
public final class HomeDirectory extends Directory {

    private static final String JS = "js";

    private File js;

    public HomeDirectory(final File directory) {
        super(directory);
    }

    public HomeDirectory init() {
        js = validate(newFile(JS));
        return this;
    }
}
