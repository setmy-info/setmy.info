package info.setmy.microservice.service;

import info.setmy.microservice.domain.ExampleModel;
import info.setmy.microservice.it.SpringBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class ExampleServiceIT extends SpringBase {

    @Autowired
    public ExampleService exampleService;

    @Test
    void getHibernateExampleModel() {
        var example = exampleService.getHibernateExampleModel();
        assertThat(example).isNotNull();
        assertThat(example.getText()).isEqualTo("Hello World from DB");
    }

    @Test
    void saveExampleModel() {
        ExampleModel model = ExampleModel.builder().text("Hello World").build();
        var result = exampleService.save(model);
        assertThat(result.getId()).isNotNull();
    }
}
