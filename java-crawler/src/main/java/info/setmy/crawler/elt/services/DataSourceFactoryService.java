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
        final String dbFileName = dbFileName(traversal);
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

    /**
     * Name of the DB file (without .mv.db) of the timestamp of the traversal: one DB per run.
     */
    public String dbFileName(final DataConnectionTraversal traversal) {
        return traversal.timestamp().format(TIMESTAMP_FORMATTER);
    }

    /**
     * Closes the Hikari pool: H2 closes the DB file with the last connection.
     */
    public void close(final DataConnectionTraversal traversal) {
        if (traversal.dataSource() instanceof HikariDataSource hikariDataSource) {
            hikariDataSource.close();
        }
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
