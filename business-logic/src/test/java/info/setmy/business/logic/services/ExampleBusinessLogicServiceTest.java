package info.setmy.business.logic.services;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ExampleBusinessLogicServiceTest {

    @Test
    void foo() {
        assertThat(ExampleBusinessLogicService.getInstance().foo()).isEqualTo("Hello from foo");
    }

    @Test
    void singleton() {
        assertThat(ExampleBusinessLogicService.getInstance()).isSameAs(ExampleBusinessLogicService.getInstance());
    }
}
