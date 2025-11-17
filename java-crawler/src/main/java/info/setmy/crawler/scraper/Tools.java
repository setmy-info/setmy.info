package info.setmy.crawler.scraper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import static info.setmy.crawler.scraper.Scraper.newScraper;

public record Tools(
    Scraper scraper,
    ObjectMapper objectMapper
) {

    public static Tools newTools(final ScraperConfig scraperConfig) {
        Scraper scraper = newScraper(scraperConfig);
        return new Tools(scraper, new ObjectMapper());
    }

    public void parse(String url, File file) {
        final ScrapedContent scrapedContent = scraper.parse(url);
        try {
            objectMapper.writeValue(file, scrapedContent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String parse(String url) {
        final ScrapedContent scrapedContent = scraper.parse(url);
        try {
            return objectMapper.writeValueAsString(scrapedContent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
