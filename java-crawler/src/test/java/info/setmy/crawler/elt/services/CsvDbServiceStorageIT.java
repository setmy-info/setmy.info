package info.setmy.crawler.elt.services;

import info.setmy.crawler.elt.models.GuiceCreation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.google.inject.Key.get;
import static com.google.inject.name.Names.named;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * csvDb through Guice like the picocli command: Hikari, Liquibase, Hibernate and jOOQ on a temporary directory.
 */
class CsvDbServiceStorageIT {

    @TempDir
    Path directory;

    GuiceService guiceService;

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(directory.resolve("input"));
        guiceService = GuiceService.newGuiceService(
                GuiceCreation.builder()
                    .homeDirectory(directory.toFile())
                    .workingDirectory(directory.toFile())
                    .build()
            )
            .init();
    }

    @Test
    void csvRowsAreStoredAndFilesMoved() throws IOException, SQLException {
        writeInput("a.csv", "\"name\";\"url\"\n"
            + "\"Eesti põhiseadus\";\"https://www.riigiteataja.ee/akt/115052015002\"\n"
            + "\"Semi; colon \"\"quoted\"\"\";\"https://example.com/a\"\n");
        writeInput("b.csv", "\"name\";\"url\"\n\"B\";\"https://example.com/b\"\n");

        csvDb().run();

        final List<String> names = names(singleDatabase());
        assertThat(names).containsExactly("Eesti põhiseadus", "Semi; colon \"quoted\"", "B");
        assertThat(files("input")).isEmpty();
        assertThat(files("processed")).hasSize(2);
        assertThat(files("error")).isEmpty();
    }

    @Test
    void badFileGoesToErrorAndGoodFilesAreStored() throws IOException, SQLException {
        writeInput("a.csv", "\"name\";\"url\"\n\"A\";\"https://example.com/a\"\n");
        writeInput("b.csv", "\"title\";\"link\"\n\"B\";\"https://example.com/b\"\n");

        csvDb().run();

        assertThat(names(singleDatabase())).containsExactly("A");
        assertThat(files("input")).isEmpty();
        assertThat(files("processed")).containsExactly("a.csv");
        assertThat(files("error")).containsExactly("b.csv");
    }

    @Test
    void sameFileNameAgainDoesNotOverwriteTheProcessedFile() throws IOException {
        writeInput("a.csv", "\"name\";\"url\"\n\"A1\";\"https://example.com/1\"\n");
        csvDb().run();
        writeInput("a.csv", "\"name\";\"url\"\n\"A2\";\"https://example.com/2\"\n");
        csvDb().run();

        assertThat(files("input")).isEmpty();
        assertThat(files("processed")).containsExactly("a.csv", "a.csv");
        assertThat(files("error")).isEmpty();
    }

    private Runnable csvDb() {
        return guiceService.getInjector().getInstance(get(CsvDbService.class, named("csvDb")));
    }

    private void writeInput(final String name, final String content) throws IOException {
        Files.writeString(directory.resolve("input").resolve(name), content, StandardCharsets.UTF_8);
    }

    private File singleDatabase() throws IOException {
        try (Stream<Path> files = Files.list(directory.resolve("output"))) {
            final List<Path> databases = files.filter(file -> file.toString().endsWith(".mv.db")).toList();
            assertThat(databases).hasSize(1);
            return databases.getFirst().toFile();
        }
    }

    /**
     * Plain JDBC, independent of the tools under the test.
     */
    private static List<String> names(final File database) throws SQLException {
        final String base = database.getAbsolutePath().replaceFirst("\\.mv\\.db$", "");
        final List<String> names = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:h2:" + base + ";IFEXISTS=TRUE", "sa", "");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select name from record_entity order by id")) {
            while (resultSet.next()) {
                names.add(resultSet.getString(1));
            }
        }
        return names;
    }

    /**
     * File names in the directory and its subdirectories.
     */
    private List<String> files(final String name) throws IOException {
        final Path root = directory.resolve(name);
        if (!Files.isDirectory(root)) {
            return List.of();
        }
        try (Stream<Path> files = Files.walk(root)) {
            return files.filter(Files::isRegularFile).map(file -> file.getFileName().toString()).sorted().toList();
        }
    }
}
