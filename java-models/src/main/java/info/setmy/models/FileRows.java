package info.setmy.models;

import info.setmy.exceptions.NotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import static java.util.Collections.unmodifiableList;
import static java.util.Objects.nonNull;

public class FileRows {

    private final File file;

    private List<String> result;

    public FileRows(final String fileName) {
        this(new File(fileName));
    }

    public FileRows(final File file) {
        this.file = file;
    }

    public List<String> getRows() {
        if (nonNull(result)) {
            return result;
        }
        final List<String> newResult = new ArrayList<>();
        getRows(row -> newResult.add(row));
        result = unmodifiableList(newResult);
        return result;
    }

    public void getRows(final Consumer<String> consumer) {
        try (final Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                consumer.accept(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new NotFoundException(e);
        }
    }
}
