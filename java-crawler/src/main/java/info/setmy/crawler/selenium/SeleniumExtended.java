package info.setmy.crawler.selenium;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.JavascriptExecutor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.io.FileUtils.readFileToString;

@Getter
@RequiredArgsConstructor
public final class SeleniumExtended {

    private final Selenium selenium;
    private final SeleniumExtendedConfig seleniumExtendedConfig;

    public void init() {
        selenium.init();
        selenium.initSize();
        selenium.initTimeouts();
    }

    public void executeScripts() {
        executeScripts(seleniumExtendedConfig.scriptNames());
    }

    public void executeScripts(final List<String> scriptFileNames) {
        final String fullScript = scriptFileNames.stream()
            .map(scriptFileName -> loadResource(scriptFileName))
            .reduce("", (final String collectedScripts, final String scriptContent) -> new StringBuilder()
                .append(collectedScripts)
                .append("\n")
                .append(scriptContent)
                .toString());
        executeScript(fullScript);
    }

    public String loadResource(final String path) {
        final File file = new File(path);
        if (file.exists() && file.isFile()) {
            try {
                return readFileToString(file, UTF_8);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read file from filesystem: " + path, e);
            }
        }

        final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        if (inputStream != null) {
            try {
                return new String(inputStream.readAllBytes(), UTF_8);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read resource from classpath: " + path, e);
            }
        }
        throw new RuntimeException("Resource not found: " + path);
    }

    private void executeScript(final String javaScript) {
        ((JavascriptExecutor) selenium.getWebDriver()).executeScript(javaScript);
    }
}
