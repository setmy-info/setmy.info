package info.setmy.microservice.service;

import info.setmy.microservice.it.SpringBase;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ExampleServiceIT extends SpringBase {

    @Autowired
    public ExampleService exampleService;

    @Test
    public void getHibernateExampleModel() {
        var example = exampleService.getHibernateExampleModel();
        assertThat(example).isNotNull();
        assertThat(example.getText()).isEqualTo("Hello World from DB");
    }
}
