package info.setmy.crawler.scraper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class ScraperIT {

    private final static String TEST_NAME = ScraperIT.class.getSimpleName();

    Scraper scraper;
    ScraperConfig scraperConfig;

    @BeforeEach
    public void before() {
        scraperConfig = new ScraperConfig("localhost", 4444);
        scraperConfig.addScript("setmy-info.github.io", getFileName("ScraperIT.js"));
        scraperConfig.addScript("setmy-info.github.io", getFileName("smiTextSearchService.js"));
        scraper = new Scraper(scraperConfig);
    }

    @Test
    @Disabled
    public void loremIpsum() {
        final Text text = scraper.parse("https://setmy-info.github.io/src/site/markdown/loremipsum.html");
        final String someTexts = text.getScrapedText().stream()
            .skip(10)
            .limit(20)
            .map(ScrapedText::toString)
            .collect(Collectors.joining("\n"));
        assertThat(text.getTitle()).isEqualTo("Lorem Ipsum | setmy.info");
        assertThat(someTexts).containsSubsequence("Ei ole ka kedagi, kes armastab ja otsib ja tahab valu iseennast, lihtsalt sellepärast, et see on valu...");
    }

    private static String getFileName(final String name) {
        return "./src/test/resources/" + TEST_NAME + "/" + name;
    }
}
