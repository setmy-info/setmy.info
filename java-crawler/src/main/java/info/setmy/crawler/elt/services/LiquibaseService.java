package info.setmy.crawler.elt.services;

import info.setmy.crawler.elt.models.DataConnectionTraversal;
import jakarta.inject.Inject;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;

@Log4j2
@Getter
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class LiquibaseService {

    public DataConnectionTraversal migrate(final DataConnectionTraversal dataConnectionTraversal) {
        try (Connection connection = dataConnectionTraversal.dataSource().getConnection()) {
            final Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(connection));
            final Liquibase liquibase = new Liquibase(
                dataConnectionTraversal.changeLogFile(),
                new ClassLoaderResourceAccessor(),
                database
            );
            liquibase.update();
            log.info("Liquibase migration completed successfully");

        } catch (Exception e) {
            log.error("Liquibase migration failed", e);
            throw new RuntimeException("Database migration failed", e);
        }
        return dataConnectionTraversal;
    }
}
