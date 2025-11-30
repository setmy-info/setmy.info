package info.setmy.crawler.elt.services;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import info.setmy.crawler.dal.DataSourceConfig;
import info.setmy.crawler.elt.models.DataConnectionTraversal;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.sql.DataSource;
import java.time.format.DateTimeFormatter;

@Log4j2
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class DataSourceFactoryService {

    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("ddMMyyyyHHmmssSSS");

    private final GlobalConfigService globalConfigService;

    public DataConnectionTraversal fillDataSourcePerFile(final DataConnectionTraversal traversal) {
        final DataSourceConfig dataSourceConfig = DataSourceConfig.builder()
            .jdbcUrl("jdbc:h2:" + globalConfigService.getWorkingDirectory().getOutput().getAbsolutePath() + "/" + traversal.csvFile().getName())
            .userName(traversal.userName())
            .password(traversal.password())
            .maximumPoolSize(5)
            .build();
        return traversal.toBuilder()
            .dataSource(newDataSource(dataSourceConfig))
            .build();
    }

    public DataConnectionTraversal fillDataSourcePerFileWithTimestamp(final DataConnectionTraversal traversal) {
        final String dbFileName = traversal.timestamp().format(TIMESTAMP_FORMATTER);
        final DataSourceConfig dataSourceConfig = DataSourceConfig.builder()
            .jdbcUrl("jdbc:h2:" + globalConfigService.getWorkingDirectory().getOutput().getAbsolutePath() + "/" + dbFileName)
            .userName(traversal.userName())
            .password(traversal.password())
            .maximumPoolSize(5)
            .build();

        return traversal.toBuilder()
            .dataSource(newDataSource(dataSourceConfig))
            .build();
    }

    private DataSource newDataSource(final DataSourceConfig dataSourceConfig) {
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(dataSourceConfig.jdbcUrl());
        hikariConfig.setUsername(dataSourceConfig.userName());
        hikariConfig.setPassword(dataSourceConfig.password());
        hikariConfig.setMaximumPoolSize(dataSourceConfig.maximumPoolSize());
        return new HikariDataSource(hikariConfig);
    }
}
