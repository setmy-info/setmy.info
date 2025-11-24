package info.setmy.crawler.scraper.camel;

import info.setmy.crawler.scraper.models.HomeDirectory;
import info.setmy.crawler.scraper.models.WorkingDirectory;
import lombok.Builder;

@Builder(toBuilder = true)
public record ScraperRouteBuilderConfig(
    String routeId,
    String executorPoolId,
    HomeDirectory homeDirectory,
    WorkingDirectory workingDirectory
) {
}
