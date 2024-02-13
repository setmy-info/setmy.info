package info.setmy.crawler.scraper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static java.time.Duration.ofSeconds;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.openqa.selenium.By.id;

public class Scraper {

    private final ScraperConfig scraperConfig;

    public Scraper(final ScraperConfig scraperConfig) {
        this.scraperConfig = scraperConfig;
    }

    public Text parse(final String urlString) {
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        final WebDriver driver = new RemoteWebDriver(scraperConfig.getUrl(), capabilities);
        driver.get(urlString);
        final Duration duration = ofSeconds(10);
        driver.manage().timeouts().implicitlyWait(duration);
        scraperConfig.findScripts(urlString).forEach(script -> {
            try {
                final String javaScript = readFileToString(new File(script));
                ((JavascriptExecutor) driver).executeScript(javaScript);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        final WebElement smiTextArea = driver.findElement(id("smiTextArea"));
        //final WebDriverWait wait = new WebDriverWait(driver, duration);
        //wait.until(ExpectedConditions.visibilityOf(smiTextArea));
        final String smiTextAreaText = smiTextArea.getAttribute("value");
        System.out.println("smiTextAreaText: " + smiTextAreaText);
        final Text text = new Text();
        text.setTitle(driver.getTitle());
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final ScrapedText[] result = objectMapper.readValue(smiTextAreaText, ScrapedText[].class);
            text.setScrapedText(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
        return text;
    }
}
