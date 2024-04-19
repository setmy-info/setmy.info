package info.setmy.microservice;

import info.setmy.microservice.it.SpringBase;
import info.setmy.microservice.property.ExampleProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ApplicationIT extends SpringBase {

    @Autowired
    private ExampleProperties exampleProperties;

    @Test
    void contextLoads() {
    }

    @Test
    void exampleProperties() {
        assertThat(exampleProperties.getFoo()).isEqualTo("Integration test application.yaml");
    }
}
