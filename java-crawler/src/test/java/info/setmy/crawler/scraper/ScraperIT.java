package info.setmy.crawler.scraper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;


public class ScraperIT {

    Scraper scraper;
    ScraperConfig scraperConfig;

    @BeforeEach
    public void before() {
        scraperConfig = new ScraperConfig("localhost", 4444);
        scraperConfig.addScript("setmy-info.codeberg.page", getFileName("smiControls.js"));
        scraperConfig.addScript("setmy-info.codeberg.page", getFileName("ScraperIT.js"));
        scraperConfig.addScript("setmy-info.codeberg.page", getFileName("smiTextSearchService.js"));
        scraper = new Scraper(scraperConfig);
    }

    @Test
    public void loremIpsum() {
        final ScrapedContent scrapedContent = scraper.parse("https://setmy-info.codeberg.page/loremipsum.html");
        final String someTexts = scrapedContent.getScrapedTexts().stream()
            .map(ScrapedText::toString)
            .filter(s -> !s.isBlank())
            .collect(Collectors.joining("\n"));
        assertThat(scrapedContent.getScrapedTexts()).hasSize(15);
        assertThat(scrapedContent.getScrapedTexts().get(12).getNodeName()).isEqualTo("span");
        assertThat(scrapedContent.getScrapedTexts().get(12).getText()).isEqualTo("This page was made manually");
        assertThat(scrapedContent.getScrapedTexts().get(12).getPaddingTop()).isCloseTo(48, offset(5));
        assertThat(scrapedContent.getScrapedTexts().get(12).getMarginTop()).isCloseTo(64, offset(5));
        assertThat(scrapedContent.getScrapedTexts().get(12).getLocation()).isEqualTo("0:html;1:body;1:main;9:footer;0:span");
        assertThat(scrapedContent.getScrapedTexts().get(12).getLocationArray()).hasSize(5);
        assertThat(scrapedContent.getScrapedTexts().get(12).getLocationArray()[0].getIndex()).isEqualTo(0L);
        assertThat(scrapedContent.getScrapedTexts().get(12).getLocationArray()[0].getName()).isEqualTo("html");
        assertThat(scrapedContent.getScrapedTexts().get(12).getLocationArray()[1].getIndex()).isEqualTo(1L);
        assertThat(scrapedContent.getScrapedTexts().get(12).getLocationArray()[1].getName()).isEqualTo("body");
        assertThat(scrapedContent.getScrapedTexts().get(12).getLocationArray()[2].getIndex()).isEqualTo(1L);
        assertThat(scrapedContent.getScrapedTexts().get(12).getLocationArray()[2].getName()).isEqualTo("main");
        assertThat(scrapedContent.getScrapedTexts().get(12).getLocationArray()[3].getIndex()).isEqualTo(9L);
        assertThat(scrapedContent.getScrapedTexts().get(12).getLocationArray()[3].getName()).isEqualTo("footer");
        assertThat(scrapedContent.getScrapedTexts().get(12).getLocationArray()[4].getIndex()).isEqualTo(0L);
        assertThat(scrapedContent.getScrapedTexts().get(12).getLocationArray()[4].getName()).isEqualTo("span");
        assertThat(scrapedContent.getScrapedTexts().get(14).getText()).isEqualTo(".");
        assertThat(someTexts).containsSubsequence("\"Ei ole ka kedagi, kes armastab ja otsib ja tahab valu iseennast, lihtsalt");
        assertThat(scrapedContent.getUrl()).isEqualTo("https://setmy-info.codeberg.page/loremipsum.html");
    }

    private String getFileName(final String name) {
        return "./src/test/resources/" + getClass().getSimpleName() + "/" + name;
    }
}
