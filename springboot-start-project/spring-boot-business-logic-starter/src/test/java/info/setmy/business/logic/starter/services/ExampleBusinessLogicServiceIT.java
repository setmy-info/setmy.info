package info.setmy.business.logic.starter.services;

import info.setmy.business.logic.services.ExampleBusinessLogicService;
import info.setmy.business.logic.starter.it.SpringBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The plain Java singleton of business-logic-starter, used as a Spring bean.
 */
class ExampleBusinessLogicServiceIT extends SpringBase {

    @Autowired
    private ExampleBusinessLogicService exampleBusinessLogicService;

    @Test
    void foo() {
        assertThat(exampleBusinessLogicService.foo()).isEqualTo("Hello from foo");
    }

    @Test
    void beanIsTheLibrarySingleton() {
        assertThat(exampleBusinessLogicService).isSameAs(ExampleBusinessLogicService.getInstance());
    }
}
