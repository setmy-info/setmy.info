package info.setmy.models

import org.junit.Before
import org.junit.Test
import org.yaml.snakeyaml.Yaml

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
class YamlTest {
    
    Yaml parser

    @Before
    public void setUp() {
        parser = new Yaml()
    }

    @Test
    public void yamlToMapParsing() {
        Map<String, Object> parsed = parser.load(("src/test/resources/test.yml" as File).text)
        assert parsed["object"].numberValue == 123
        assert parsed["object"].booleanValue ==  true
        assert parsed["object"].stringValue ==  "Hello World"
        assert parsed["object"]["subObject"].numberValue ==  321
        assert parsed["object"]["subObject"].booleanValue ==  false
        assert parsed["object"]["subObject"].stringValue ==  "World Hello!"
    }
}

