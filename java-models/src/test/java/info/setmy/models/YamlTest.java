package info.setmy.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.yaml.snakeyaml.Yaml;

public class YamlTest {

    Yaml parser;

    @Before
    public void setUp() {
        parser = new Yaml();
    }

    @Test
    public void yamlToMapParsing() throws FileNotFoundException {
        final Map<String, Object> map = (Map) parser.load(new FileInputStream(new File("src/test/resources/test.yml")));//parser.load("hello: 25");
        assertThat((int) ((Map<String, Object>) map.get("object")).get("numberValue"), is(equalTo(123)));
        assertThat((boolean) ((Map<String, Object>) map.get("object")).get("booleanValue"), is(equalTo(true)));
        assertThat((String) ((Map<String, Object>) map.get("object")).get("stringValue"), is(equalTo("Hello World")));
        assertThat((int) ((Map<String, Object>) ((Map<String, Object>) map.get("object")).get("subObject")).get("numberValue"), is(equalTo(321)));
        assertThat((boolean) ((Map<String, Object>) ((Map<String, Object>) map.get("object")).get("subObject")).get("booleanValue"), is(equalTo(false)));
        assertThat((String) ((Map<String, Object>) ((Map<String, Object>) map.get("object")).get("subObject")).get("stringValue"), is(equalTo("World Hello!")));
    }
}
