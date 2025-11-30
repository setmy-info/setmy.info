package info.setmy.crawler.elt.services;

import info.setmy.crawler.elt.models.DataConnectionTraversal;
import info.setmy.crawler.entities.RecordEntity;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.util.Objects.requireNonNull;

@Log4j2
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class TransformsService {

    private static final CSVFormat CSV_FORMAT = CSVFormat.DEFAULT.builder()
        .setDelimiter(';')
        .setQuote('"')
        .setEscape('"')
        .setHeader()
        .setSkipHeaderRecord(true)
        .setTrim(true)
        .setIgnoreEmptyLines(true)
        .get();
    private static final int BATCH_SIZE = 10;

    private final GlobalConfigService globalConfigService;
    private final DataSourceFactoryService dataSourceFactoryService;
    private final LiquibaseService liquibaseService;
    private final HibernateService hibernateService;
    private final JOOQService jooqService;

    public void csvToDb() {
        final DataConnectionTraversal dataConnectionTraversal = DataConnectionTraversal.builder()
            .timestamp(now())
            .userName("sa")
            .password("")
            .csvFiles(getCSVFiles())
            .persistenceUnitName("appPU")
            .changeLogFile("db/changelog/db.changelog-master.xml")
            .build();

        final DataConnectionTraversal dbInitialized =
            jooqService.fillJooq(
                hibernateService.fillEntityManagerFactory(
                    hibernateService.fillHibernateProperties(
                        liquibaseService.migrate(
                            dataSourceFactoryService.fillDataSourcePerFileWithTimestamp(dataConnectionTraversal)
                        )
                    )
                )
            );

        dataConnectionTraversal.csvFiles().forEach(file -> {
            final DataConnectionTraversal csvFileTraversal = dbInitialized.toBuilder()
                .csvFile(file)
                .build();
            rowsToDb(csvFileTraversal);
        });
    }

    private List<File> getCSVFiles() {
        return Arrays.stream(requireNonNull(globalConfigService.getWorkingDirectory().getInput().listFiles()))
            .sorted()
            .filter(file -> file.isFile() && file.getName().toLowerCase().endsWith(".csv"))
            .toList();
    }

    private void rowsToDb(final DataConnectionTraversal dbInitialized) {
        try (Reader reader = Files.newBufferedReader(dbInitialized.csvFile().toPath(), StandardCharsets.UTF_8);
             final CSVParser parser = CSVParser.parse(reader, CSV_FORMAT)) {
            for (CSVRecord record : parser) {
                final DataConnectionTraversal withDbRecord = dbInitialized.toBuilder()
                    .recordEntity(RecordEntity.builder()
                        .url(record.get("url"))
                        .name(record.get("name"))
                        .build()
                    )
                    .build();
                save(withDbRecord);
            }
        } catch (IOException ex) {
            log.error("IO error with CSV file {}", dbInitialized.csvFile(), ex);
        }
    }

    private void save(final DataConnectionTraversal withDbRecord) {
        final DataConnectionTraversal withEntityManager = hibernateService.fillEntityManager(withDbRecord);
        final RecordEntity recordEntity = withDbRecord.recordEntity();
        log.info("DB record: {} : {}", recordEntity.getName(), recordEntity.getUrl());
        final EntityManager entityManager = withEntityManager.entityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(recordEntity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            log.error("Failed to save record", e);
            throw e;
        }
    }
}
