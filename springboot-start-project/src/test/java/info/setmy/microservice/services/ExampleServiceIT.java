package info.setmy.microservice.services;

import info.setmy.microservice.SpringIntegrationTestBase;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ExampleServiceIT extends SpringIntegrationTestBase {

    @Autowired
    ExampleService exampleService;

    @Test
    public void getAsyncCallsResults() {
        final String result = exampleService.getAsyncCallsResults();
        assertThat(result).isEqualTo("Foo:Bar:More");
    }
}
