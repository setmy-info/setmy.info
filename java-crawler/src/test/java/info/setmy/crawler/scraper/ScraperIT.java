package info.setmy.crawler.scraper;

import info.setmy.crawler.scraper.tools.Tools;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.stream.Collectors;

import static info.setmy.crawler.scraper.tools.Tools.newTools;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;


public class ScraperIT {

    Scraper scraper;
    Tools tools;
    ScraperConfig scraperConfig;

    @BeforeEach
    public void before() {
        scraperConfig = new ScraperConfig("localhost", 4444, false);
        scraperConfig.addScript("setmy-info.codeberg.page", getFileName("smiControls.js"));
        scraperConfig.addScript("setmy-info.codeberg.page", getFileName("ScraperIT.js"));
        scraperConfig.addScript("setmy-info.codeberg.page", getFileName("smiTextSearchService.js"));

        scraperConfig.addScript("maven.apache.org", getFileName("smiControls.js"));
        scraperConfig.addScript("maven.apache.org", getFileName("ScraperIT.js"));
        scraperConfig.addScript("maven.apache.org", getFileName("smiTextSearchService.js"));

        scraperConfig.addScript("localhost", getFileName("smiControls.js"));
        scraperConfig.addScript("localhost", getFileName("ScraperIT.js"));
        scraperConfig.addScript("localhost", getFileName("smiTextSearchService.js"));
        tools = newTools(scraperConfig);
        scraper = tools.scraper();
    }

    @Test
    @Disabled
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

    @Test
    @Disabled
    public void maven() {
        final ScrapedContent scrapedContent = scraper.parse("https://maven.apache.org/download.cgi");
        final String someTexts = scrapedContent.getScrapedTexts().stream()
            .map(ScrapedText::toString)
            .filter(s -> !s.isBlank())
            .collect(Collectors.joining("\n"));
    }

    @Test
    @Disabled
    public void local() {
        final ScrapedContent scrapedContent = scraper.parse("http://localhost:7171/");
        final String someTexts = scrapedContent.getScrapedTexts().stream()
            .map(ScrapedText::toString)
            .filter(s -> !s.isBlank())
            .collect(Collectors.joining("\n"));
    }

    @Test
    @Disabled
    public void local_pdf() {
        final ScrapedContent scrapedContent = scraper.parse("http://localhost:7171/pdf");
        final String someTexts = scrapedContent.getScrapedTexts().stream()
            .map(ScrapedText::toString)
            .filter(s -> !s.isBlank())
            .collect(Collectors.joining("\n"));
    }

    @Test
    @Disabled
    public void tools_url_to_file() {
        tools.parse("http://localhost:7171/pdf", new File("./target/local.pdf.json"));
    }

    private String getFileName(final String name) {
        return "./src/test/resources/" + getClass().getSimpleName() + "/" + name;
    }
}
