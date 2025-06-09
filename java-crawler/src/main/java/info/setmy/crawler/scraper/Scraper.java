package info.setmy.crawler.scraper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;

import static java.time.Duration.ofSeconds;
import static javax.swing.UIManager.put;

public class Scraper {

    private final ScraperConfig scraperConfig;

    public Scraper(final ScraperConfig scraperConfig) {
        this.scraperConfig = scraperConfig;
    }

    public ScrapedContent parse(final String urlString) {
        final ScrapedContent scrapedContent = new ScrapedContent(urlString);

        final Selenium selenium = newSelenium();
        selenium.get(scrapedContent.getUrl());

        final Duration duration = ofSeconds(10);
        selenium.getWebDriver().manage().timeouts().implicitlyWait(duration);

        selenium.executeScripts(scraperConfig.findScripts(scrapedContent.getUrl()));

        selenium.findElementByIdValue("smiTextArea")
            .ifPresent(smiTextAreaText -> scrapedContent.setScrapedTexts(
                parseScrapedTexts(smiTextAreaText)
            ));

        selenium.quit();
        return scrapedContent;
    }

    private ScrapedText[] parseScrapedTexts(final String jsonString) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, ScrapedText[].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Selenium newSelenium() {
        return new Selenium(
            new RemoteWebDriver(
                scraperConfig.getUrl(),
                newDesiredCapabilities()
            )
        );
    }

    private Capabilities newDesiredCapabilities() {
        /*
        final FirefoxProfile profile = new FirefoxProfile(new File("C:\\Users\\ccccc\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\selenium-mitm.default-release"));
        final Proxy proxy = new Proxy()
            .setHttpProxy("localhost:8888")
            .setSslProxy("localhost:8888");
        */
        final FirefoxOptions desiredCapabilities = new FirefoxOptions();
        //desiredCapabilities.setProfile(profile);
        //desiredCapabilities.setProxy(proxy);
        //desiredCapabilities.setAcceptInsecureCerts(true);

        //desiredCapabilities.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        //RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
        /*
        final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("firefox");
        */
        desiredCapabilities.setCapability("se:headers", new HashMap<String, String>() {{
            put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:122.0) Gecko/20100101 Firefox/122.0");
        }});
        return desiredCapabilities;
    }
}
