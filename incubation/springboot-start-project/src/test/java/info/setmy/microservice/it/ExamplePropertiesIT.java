package info.setmy.microservice.it;

import info.setmy.microservice.property.ExampleProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExamplePropertiesIT extends SpringBase {

    @Autowired
    private ExampleProperties exampleProperties;

    @Test
    void exampleProperties() {
        assertThat(exampleProperties.getFoo()).isEqualTo("Integration test application.yaml");
    }
}
