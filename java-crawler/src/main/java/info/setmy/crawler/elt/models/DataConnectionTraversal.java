package info.setmy.crawler.elt.models;

import info.setmy.crawler.entities.RecordEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.Builder;
import org.jooq.DSLContext;

import javax.sql.DataSource;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Builder(toBuilder = true)
public record DataConnectionTraversal(
    LocalDateTime timestamp,
    String userName,//DB user
    String password,// DB password
    List<File> csvFiles,
    File csvFile,
    DataSource dataSource,//Hikari related
    String changeLogFile,//Liquibase related
    Map<String, Object> hibernateProperties,
    String persistenceUnitName,//EMF
    EntityManagerFactory entityManagerFactory,
//EMF - Hibernate related - costly creation. Per datasource / per persistence-unit. Thread savfe.
    EntityManager entityManager,//EM - per transaction or request created each time. Per Hibernate Session.
    DSLContext dslContext,//JOOQ,
    RecordEntity recordEntity
) {
}
