package info.setmy.microservice.bean;

import info.setmy.business.logic.services.ExampleBusinessLogicService;
import info.setmy.business.logic.services.ExampleGreetingService;
import info.setmy.locator.InstanceProvider;
import info.setmy.locator.ServiceLocator;
import info.setmy.microservice.it.SpringBase;
import info.setmy.microservice.service.ExampleGreetingServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * The lower level {@link ServiceLocator} finds the implementation that the application made of a lower level
 * interface.
 */
class ServiceLocatorIT extends SpringBase {

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private InstanceProvider instanceProvider;

    @Autowired
    private ExampleGreetingService exampleGreetingService;

    @Autowired
    private ExampleBusinessLogicService exampleBusinessLogicService;

    @Test
    void beanIsTheLibrarySingletonWithSpringProvider() {
        assertThat(serviceLocator).isSameAs(ServiceLocator.getInstance());
        assertThat(instanceProvider).isInstanceOf(SpringInstanceProvider.class);
        assertThat(serviceLocator.hasInstanceProvider()).isTrue();
    }

    @Test
    void byClassTyped() {
        final ExampleGreetingService greetingService = ServiceLocator.getInstance().get(ExampleGreetingService.class);

        assertThat(greetingService).isSameAs(exampleGreetingService);
    }

    @Test
    void byName() {
        assertThat(ServiceLocator.getInstance().get(ExampleGreetingServiceImpl.BEAN_NAME))
            .isSameAs(exampleGreetingService);
    }

    @Test
    void byNameTyped() {
        final ExampleGreetingService greetingService = ServiceLocator.getInstance()
            .get(ExampleGreetingServiceImpl.BEAN_NAME, ExampleGreetingService.class);

        assertThat(greetingService.greet("Mari")).isEqualTo("Hello, Mari! (from Spring)");
    }

    @Test
    void notFound() {
        assertThatThrownBy(() -> ServiceLocator.getInstance().get("unknown"))
            .isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Test
    void lowerLevelServiceUsesApplicationImplementation() {
        assertThat(exampleBusinessLogicService.bar("Mari")).isEqualTo("Hello, Mari! (from Spring)");
    }
}
