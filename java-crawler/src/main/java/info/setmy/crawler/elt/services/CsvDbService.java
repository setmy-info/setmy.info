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
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.util.Objects.requireNonNull;

@Log4j2
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class CsvDbService implements Runnable{

    private static final CSVFormat CSV_FORMAT = CSVFormat.DEFAULT.builder()
        .setDelimiter(';')
        .setQuote('"')
        .setEscape('"')
        .setHeader()
        .setSkipHeaderRecord(true)
        .setTrim(true)
        .setIgnoreEmptyLines(true)
        .get();
    private static final int BATCH_SIZE = HibernateService.JDBC_BATCH_SIZE;

    private final GlobalConfigService globalConfigService;
    private final DataSourceFactoryService dataSourceFactoryService;
    private final LiquibaseService liquibaseService;
    private final HibernateService hibernateService;
    private final JOOQService jooqService;

    public void run() {
        final DataConnectionTraversal dataConnectionTraversal = DataConnectionTraversal.builder()
            .timestamp(now())
            .userName("sa")
            .password("")
            .csvFiles(getCSVFiles())
            .persistenceUnitName("csvPersistenceUnit")
            .changeLogFile("db/changelog/csv/db.changelog-master.xml")
            .build();

        final DataConnectionTraversal withDataSource =
            dataSourceFactoryService.fillDataSourcePerFileWithTimestamp(dataConnectionTraversal);
        DataConnectionTraversal dbInitialized = withDataSource;
        try {
            dbInitialized =
                jooqService.fillJooq(
                    hibernateService.fillEntityManagerFactory(
                        hibernateService.fillHibernateProperties(
                            liquibaseService.migrate(withDataSource)
                        )
                    )
                );

            final DataConnectionTraversal initialized = dbInitialized;
            dataConnectionTraversal.csvFiles().forEach(file -> {
                final DataConnectionTraversal csvFileTraversal = initialized.toBuilder()
                    .csvFile(file)
                    .build();
                rowsToDb(csvFileTraversal);
            });
            log.info("DB {} has {} records", dataSourceFactoryService.dbFileName(dbInitialized),
                jooqService.countRecords(dbInitialized));
        } finally {
            hibernateService.close(dbInitialized);
            dataSourceFactoryService.close(withDataSource);
        }
    }

    private List<File> getCSVFiles() {
        return Arrays.stream(requireNonNull(globalConfigService.getWorkingDirectory().getInput().listFiles()))
            .sorted()
            .filter(file -> file.isFile() && file.getName().toLowerCase().endsWith(".csv"))
            .toList();
    }

    /**
     * One file in one transaction: all rows of the file are stored or none. A stored file goes to processed, a failed
     * one to error.
     */
    private void rowsToDb(final DataConnectionTraversal csvFileTraversal) {
        final DataConnectionTraversal withEntityManager = hibernateService.fillEntityManager(csvFileTraversal);
        try (EntityManager entityManager = withEntityManager.entityManager()) {
            entityManager.getTransaction().begin();
            try {
                final long rows = saveRows(withEntityManager);
                entityManager.getTransaction().commit();
                log.info("CSV file {}: {} records stored", csvFileTraversal.csvFile().getName(), rows);
            } catch (IOException | RuntimeException e) {
                if (entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().rollback();
                }
                log.error("CSV file {} not stored, moved to error", csvFileTraversal.csvFile(), e);
                moveFileTo(csvFileTraversal, globalConfigService.getWorkingDirectory().getError());
                return;
            }
        }
        moveFileTo(csvFileTraversal, globalConfigService.getWorkingDirectory().getProcessed());
    }

    private long saveRows(final DataConnectionTraversal withEntityManager) throws IOException {
        long rows = 0;
        try (Reader reader = Files.newBufferedReader(withEntityManager.csvFile().toPath(), StandardCharsets.UTF_8);
             final CSVParser parser = CSVParser.parse(reader, CSV_FORMAT)) {
            for (CSVRecord record : parser) {
                final DataConnectionTraversal withDbRecord = withEntityManager.toBuilder()
                    .recordEntity(RecordEntity.builder()
                        .name(record.get("name"))
                        .url(record.get("url"))
                        .build()
                    )
                    .build();
                save(withDbRecord);
                rows++;
                if (rows % BATCH_SIZE == 0) {
                    // Sends the JDBC batch and frees the memory of the stored entities
                    withEntityManager.entityManager().flush();
                    withEntityManager.entityManager().clear();
                }
            }
        }
        return rows;
    }

    private void save(final DataConnectionTraversal withDbRecord) {
        final RecordEntity recordEntity = withDbRecord.recordEntity();
        log.info("DB record: {} : {}", recordEntity.getName(), recordEntity.getUrl());
        withDbRecord.entityManager().persist(recordEntity);
    }

    /**
     * Moves to directory/&lt;DB file name&gt;/: the files of one run stay together, next to the DB name, and a file of
     * an earlier run with the same name is never overwritten.
     */
    private void moveFileTo(final DataConnectionTraversal dataConnectionTraversal, final File directory) {
        final File csvFile = dataConnectionTraversal.csvFile();
        final File runDirectory = new File(directory, dataSourceFactoryService.dbFileName(dataConnectionTraversal));
        moveFile(csvFile, new File(runDirectory, csvFile.getName()));
    }

    private void moveFile(final File from, final File to) {
        try {
            FileUtils.moveFile(
                from,
                to
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
