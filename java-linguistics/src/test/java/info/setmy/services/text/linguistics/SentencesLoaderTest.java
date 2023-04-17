package info.setmy.services.text.linguistics;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class SentencesLoaderTest {

    SentencesLoader sentencesLoader;

    @BeforeEach
    public void before() {
        sentencesLoader = SentencesLoader.getInstance("./src/test/resources/sentences/sentences.txt");
    }

    @Test
    public void abc() {
        final List<String> result = sentencesLoader.read();
        assertThat(sentencesLoader.get(0)).isEqualTo("Tere maailm!");
        assertThat(sentencesLoader.getResult().get(0)).isEqualTo("Tere maailm!");
        assertThat(result.get(0)).isEqualTo("Tere maailm!");
    }
}
