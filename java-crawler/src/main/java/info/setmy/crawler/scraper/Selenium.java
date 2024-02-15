package info.setmy.crawler.scraper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.openqa.selenium.By.id;

public class Selenium {

    private final WebDriver webDriver;

    public Selenium(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public Optional<WebDriver> getOptionalWebDriver() {
        return ofNullable(getWebDriver());
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void get(final String url) {
        getWebDriver().get(url);
    }

    public Optional<WebElement> findElementById(final String id) {
        return getOptionalWebDriver()
            .map(driver -> driver.findElement(id(id)));
    }

    public void quit() {
        webDriver.quit();
    }

    public void executeScript(final String fileName) {
        getOptionalWebDriver().ifPresent(driver -> {
            try {
                final String javaScript = readFileToString(new File(fileName));
                ((JavascriptExecutor) driver).executeScript(javaScript);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Optional<String> findElementByIdValue(final String id) {
        return findElementByIdAttribute(id, "value");
    }

    public Optional<String> findElementByIdAttribute(final String id, final String attribute) {
        return findElementById(id)
            .map(webElement -> webElement.getAttribute(attribute));
    }
}
