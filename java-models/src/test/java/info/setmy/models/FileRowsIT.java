package info.setmy.models;

import info.setmy.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

class FileRowsIT {

    private final static String FILE_NAME = "./src/test/resources/FileRowsIT.txt";

    @Test
    public void readRows() {
        final List<String> rows = new FileRows(FILE_NAME).getRows();
        assertThat(rows).hasSize(3).containsExactly("Row 1", "Row 2", "Row 3");
    }

    @Test
    public void readRowsException() {
        final NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            new FileRows("non.existing.file.txt").getRows();
        });
        assertThat(exception.getMessage()).isEqualTo("java.io.FileNotFoundException: non.existing.file.txt (The system cannot find the file specified)");
    }
}
