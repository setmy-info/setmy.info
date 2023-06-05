package info.setmy.models;

import info.setmy.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static info.setmy.models.FileRows.newClasspathFileRows;
import static info.setmy.models.FileRows.newFileRows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

class FileRowsIT {

    private final static String FILE_NAME = "./src/test/resources/FileRowsIT.txt";

    @Test
    public void readRows() {
        final List<String> rows = newFileRows(FILE_NAME).get().getRows();
        assertThat(rows).hasSize(3).containsExactly("Row 1", "Row 2", "Row 3");
    }

    @Test
    public void readRowsException() {
        final NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            newFileRows("non.existing.file.txt").get().getRows();
        });
        assertThat(exception.getMessage())
            .contains("java.io.FileNotFoundException: ")
            .contains("non.existing.file.txt (");
    }

    @Test
    public void readRowsWithConsumer() {
        final List<String> rows = new ArrayList<>();
        newFileRows(FILE_NAME).get().getRows(row -> rows.add(row));
        assertThat(rows).hasSize(3).containsExactly("Row 1", "Row 2", "Row 3");
    }

    @Test
    public void newClasspathFile() {
        final List<String> rows = new ArrayList<>();
        newClasspathFileRows("FileRowsIT.txt").get().getRows(row -> rows.add(row));
        assertThat(rows).hasSize(3).containsExactly("Row 1", "Row 2", "Row 3");
    }
}
