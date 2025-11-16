package info.setmy.crawler.scraper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;

import static info.setmy.crawler.scraper.UserAgentService.userAgentService;
import static java.lang.IO.println;
import static java.time.Duration.ofSeconds;

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
        final File profileDir = new FirefoxProfileFile("selenium-test").getProfileFile();
        final FirefoxProfile profile = new FirefoxProfile(profileDir);
        /*
        final Proxy proxy = new Proxy()
            .setHttpProxy("localhost:8888")
            .setSslProxy("localhost:8888");
        */
        var agent = userAgentService.randomUserAgent();
        profile.setPreference("general.useragent.override", agent);

        final FirefoxOptions desiredCapabilities = new FirefoxOptions();
        desiredCapabilities.setProfile(profile);

        //desiredCapabilities.setProxy(proxy);
        //desiredCapabilities.setAcceptInsecureCerts(true);

        return desiredCapabilities;
    }
}
