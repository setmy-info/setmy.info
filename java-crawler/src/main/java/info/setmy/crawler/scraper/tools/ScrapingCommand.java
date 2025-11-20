package info.setmy.crawler.scraper.tools;

import info.setmy.crawler.scraper.models.ScraperConfig;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class ScrapingCommand {

    private final String source;
    private final String destination;

    private final Integer poolSize;
    private final Integer maxPoolSize;
    private final Integer maxQueueSize = 0;

    private final ScraperConfig scraperConfig;
}
