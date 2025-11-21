package info.setmy.crawler.camel;

import lombok.Builder;

@Builder(toBuilder = true)
public record ScraperRouteBuilderConfig(
    String routeId,
    String executorPoolId,
    HomeDirectory homeDirectory,
    WorkingDirectory workingDirectory
) {
}
