package info.setmy.crawler.scraper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.time.Duration;
import java.util.Random;

import static info.setmy.crawler.scraper.UserAgentService.userAgentService;
import static java.time.Duration.ofSeconds;

/**
 * TODO: to make unique window
 * ✔️ 1. User-Agent done
 * ✔️ 2. Akna suurus (tehtud)
 * ❌ 3. Accept-Language
 * ❌ 4. Timezone
 * ❌ 5. navigator.webdriver removal.
 * ❌ 6. Sec-CH-UA spoofing IMPORTANT.
 * ❌ 7. Accept header spoofing.
 * ❌ 8. Intelligent pauses + scroll + mouse movements
 * */
public class Scraper {

    private final ScraperConfig scraperConfig;

    public Scraper(final ScraperConfig scraperConfig) {
        this.scraperConfig = scraperConfig;
    }

    public ScrapedContent parse(final String urlString) {
        final ScrapedContent scrapedContent = new ScrapedContent(urlString);

        final Selenium selenium = newSelenium();
        Dimension dimension = randomWindowSize();
        scrapedContent.getMetaData().setHeight(dimension.height);
        scrapedContent.getMetaData().setWidth(dimension.width);
        selenium.getWebDriver().manage().window().setSize(dimension);
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

    public Dimension randomWindowSize() {
        Random r = new Random();
        int width = 1000 + r.nextInt(600);  // 1000–1600
        int height = 700 + r.nextInt(400);   // 700–1100
        return new Dimension(width, height);
    }
}
