package info.setmy.crawler.dal;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.sql.DataSource;
import java.sql.Connection;

@Log4j2
@Getter
@RequiredArgsConstructor
public class LiquibaseComponent {

    private final DataSource dataSource;
    private final String changeLogFile;

    public LiquibaseComponent migrate() {
        try (Connection connection = dataSource.getConnection()) {
            final Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(connection));
            final Liquibase liquibase = new Liquibase(
                changeLogFile,
                new ClassLoaderResourceAccessor(),
                database
            );
            liquibase.update();
            log.info("Liquibase migration completed successfully");

        } catch (Exception e) {
            log.error("Liquibase migration failed", e);
            throw new RuntimeException("Database migration failed", e);
        }
        return this;
    }
}
