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
        js = new File(directory, JS);
        if (js.exists() && !js.isDirectory()) {
            throw new RuntimeException("Input directory exists and is not a directory");
        }
        if (!js.exists()) {
            js.mkdirs();
        }
        return this;
    }
}
