package info.setmy.services.text.linguistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class SentencesLoader {

    private static Optional<SentencesLoader> optionalSentensesLoader = empty();

    static final Consumer<Object> doNothing = (x) -> {
    };

    private final File file;

    public final static SentencesLoader getInstance(final String fileName) {
        optionalSentensesLoader.ifPresentOrElse(
            doNothing,
            () -> optionalSentensesLoader = of(new SentencesLoader(fileName)));
        return optionalSentensesLoader.get();
    }

    private SentencesLoader(final String fileName) {
        this(new File(fileName));
    }

    private SentencesLoader(final File file) {
        this.file = file;
    }

    private final List<String> result = new ArrayList<>();

    public List<String> read() {
        try (final Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                result.add(scanner.nextLine());
            }
            return result;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String get(final int index) {
        return result.get(index);
    }

    public List<String> getResult() {
        return result;
    }
}
