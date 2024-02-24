package info.setmy.linguistics;

import info.setmy.linguistics.models.token.Token;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static info.setmy.linguistics.ResourcesUtil.getTestFileName;
import static info.setmy.models.FileRows.newFileRows;
import static org.assertj.core.api.Assertions.assertThat;

public class ParserIT {

    static List<String> sentences;
    static List<List<Token>> parsedSentences;

    static final int NUMBER_OF_ROWS = 9;
    private final IntConsumer[] testFunctions = {
        this::rowNumber0,
        this::doNothing,
        this::doNothing,
        this::doNothing,
        this::doNothing,
        this::doNothing,
        this::doNothing,
        this::doNothing,
        this::doNothing,
        this::doNothing
    };

    @BeforeAll
    public static void beforeAll() {
        sentences = newFileRows(getTestFileName(ParserIT.class, "sentences.txt")).get().getRows();
        parsedSentences = sentences.stream()
            .map(sentence -> new Parser().parse(sentence))
            .collect(Collectors.toUnmodifiableList());
    }

    @ParameterizedTest
    @MethodSource("provideIntegers")
    public void sentence1(final int rowNumber) {
        testFunctions[rowNumber].accept(rowNumber);
    }

    private void rowNumber0(final int rowNumber) {
        final List<Token> parsedTokens = get(rowNumber);
        assertThat(parsedTokens).hasSize(3);
        assertThat(parsedTokens.get(0).toString()).isEqualTo("Tere");
        assertThat(parsedTokens.get(1).toString()).isEqualTo("maailm");
        assertThat(parsedTokens.get(2).toString()).isEqualTo("!");
    }

    // TODO : more tests for all rows

    private List<Token> get(final int index) {
        return parsedSentences.get(index);
    }

    private static Stream<Integer> provideIntegers() {
        return IntStream.rangeClosed(0, NUMBER_OF_ROWS - 1).boxed();
    }

    private void doNothing(final int rowNumber) {
    }
}
