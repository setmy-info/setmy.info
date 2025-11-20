package info.setmy.crawler.scraper;

import info.setmy.crawler.browser.models.Browser;
import info.setmy.crawler.camel.Camel;
import info.setmy.crawler.camel.CamelConfig;
import info.setmy.crawler.camel.CamelThreadsConfig;
import info.setmy.crawler.camel.ScraperRouteBuilder;
import info.setmy.crawler.selenium.Selenium;
import info.setmy.crawler.selenium.SeleniumConfig;
import info.setmy.crawler.selenium.SeleniumExtended;
import info.setmy.crawler.selenium.SeleniumExtendedConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;


public class ScraperIT {

    Browser browser;
    SeleniumConfig seleniumConfig;
    Selenium selenium;
    SeleniumExtendedConfig seleniumExtendedConfig;
    SeleniumExtended seleniumExtended;
    ScraperConfig scraperConfig;
    Scraper scraper;
    //String path = "src/test/resources/ScraperIT/";
    String path = "";
    CamelThreadsConfig threadsConfig;
    CamelConfig camelConfig;
    Camel camel;

    @BeforeEach
    public void before() {
        browser = Browser.newBrowser();
        seleniumConfig = SeleniumConfig.builder()
            .hostName(of("localhost"))
            .port(of(4444))
            .headless(false)
            .browser(of(browser))
            .build();
        selenium = new Selenium(seleniumConfig);
        seleniumExtendedConfig = SeleniumExtendedConfig.builder()
            .scriptNames(asList(path + "smiControls.js", path + "ScraperIT.js", path + "smiTextSearchService.js"))
            .build();
        seleniumExtended = new SeleniumExtended(selenium, seleniumExtendedConfig);

        threadsConfig = CamelThreadsConfig.builder()
            .poolSize(5)
            .maxPoolSize(5)
            .maxQueueSize(0)
            .build();
        camelConfig = CamelConfig.builder()
            .build();
        camel = new  Camel(camelConfig);
        //camel.add(new ScraperRouteBuilder(s));

        scraperConfig = new ScraperConfig();
        scraper = new Scraper(scraperConfig, seleniumExtended);
        scraper.init();
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
        assertThat(scrapedContent.getScrapedTexts().get(12).getLocation()).isEqualTo("html[0].body[1].main[1].footer[9].span[0]");
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

    /*
    @Test
    @Disabled
    public void tools_url_to_file() {
        tools.parse("http://localhost:7171/pdf", new File("./target/local.pdf.json"));
    }
    */

    private String getFileName(final String name) {
        return "./src/test/resources/" + getClass().getSimpleName() + "/" + name;
    }
}
