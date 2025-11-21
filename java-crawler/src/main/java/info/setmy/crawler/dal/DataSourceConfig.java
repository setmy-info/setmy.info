package info.setmy.crawler.dal;

import lombok.Builder;

@Builder(toBuilder = true)
public record DataSourceConfig(
    String jdbcUrl,
    String userName,
    String password,
    int maximumPoolSize
) {
}
