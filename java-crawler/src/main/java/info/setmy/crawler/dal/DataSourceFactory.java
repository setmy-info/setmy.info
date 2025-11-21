package info.setmy.crawler.dal;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {

    private static final DataSourceFactory INSTANCE = new DataSourceFactory();

    public static DataSourceFactory getInstance() {
        return INSTANCE;
    }

    public DataSource newDataSource(final DataSourceConfig dataSourceConfig) {
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(dataSourceConfig.jdbcUrl());
        hikariConfig.setUsername(dataSourceConfig.userName());
        hikariConfig.setPassword(dataSourceConfig.password());
        hikariConfig.setMaximumPoolSize(dataSourceConfig.maximumPoolSize());
        return new HikariDataSource(hikariConfig);
    }
}
