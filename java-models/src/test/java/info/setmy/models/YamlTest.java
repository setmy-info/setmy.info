package info.setmy.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

public class YamlTest {

    Yaml parser;

    @BeforeEach
    public void setUp() {
        parser = new Yaml();
    }

    @Test
    public void yamlToMapParsing() throws FileNotFoundException {
        final Map<String, Object> map = (Map) parser.load(new FileInputStream(new File("src/test/resources/test.yml")));//parser.load("hello: 25");
        assertThat((int) ((Map<String, Object>) map.get("object")).get("numberValue")).isEqualTo(123);
        assertThat((boolean) ((Map<String, Object>) map.get("object")).get("booleanValue")).isEqualTo(true);
        assertThat((String) ((Map<String, Object>) map.get("object")).get("stringValue")).isEqualTo("Hello World");
        assertThat((int) ((Map<String, Object>) ((Map<String, Object>) map.get("object")).get("subObject")).get("numberValue")).isEqualTo(321);
        assertThat((boolean) ((Map<String, Object>) ((Map<String, Object>) map.get("object")).get("subObject")).get("booleanValue")).isEqualTo(false);
        assertThat((String) ((Map<String, Object>) ((Map<String, Object>) map.get("object")).get("subObject")).get("stringValue")).isEqualTo("World Hello!");
    }
}
