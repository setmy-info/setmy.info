package info.setmy.crawler.camel;

import lombok.Builder;

@Builder(toBuilder = true)
public record CamelThreadsConfig(
    String poolId,
    Integer poolSize,
    Integer maxPoolSize,
    Integer maxQueueSize
) {
}
