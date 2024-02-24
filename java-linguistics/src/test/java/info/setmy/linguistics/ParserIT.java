package info.setmy.linguistics;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static info.setmy.models.FileRows.newFileRows;

public class ParserIT {

    Parser parser;
    static List<String> sentences;

    @BeforeAll
    public static void beforeAll() {
        sentences = newFileRows("./src/test/resources/sentences/sentences.txt").get().getRows();
    }

    @BeforeEach
    public void before() {
        parser = new Parser();
    }

    @Test
    public void failing() {

    }
}
