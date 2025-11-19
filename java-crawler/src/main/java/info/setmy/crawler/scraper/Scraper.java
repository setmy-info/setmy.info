package info.setmy.crawler.scraper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.setmy.crawler.browser.models.Browser;
import info.setmy.crawler.selenium.Selenium;
import info.setmy.crawler.selenium.SeleniumExtended;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

import static info.setmy.crawler.browser.models.Browser.newBrowser;
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
 *
 */
@Getter
@RequiredArgsConstructor
public final class Scraper {

    private final ScraperConfig scraperConfig;
    private final SeleniumExtended seleniumExtended;

    public void init() {
        seleniumExtended.init();
    }

    public ScrapedContent parse(final String urlString) {
        final ScrapedContent scrapedContent = new ScrapedContent(urlString);
        final Dimension dimension = seleniumExtended.getSelenium().getWebDriver().manage().window().getSize();
        scrapedContent.getMetaData().setHeight(dimension.height);
        scrapedContent.getMetaData().setWidth(dimension.width);

        seleniumExtended.getSelenium().get(scrapedContent.getUrl());

        seleniumExtended.executeScripts();

        seleniumExtended.getSelenium().getElementValueById("smiTextArea")
            .ifPresent(smiTextAreaText -> scrapedContent.setScrapedTexts(
                parseScrapedTexts(smiTextAreaText)
            ));

        seleniumExtended.getSelenium().quit();
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
}
