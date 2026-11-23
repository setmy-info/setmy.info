package info.setmy.business.logic.services;

import info.setmy.locator.InstanceProvider;
import info.setmy.locator.ServiceLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ServiceLocatorTest {

    static final ExampleGreetingService GREETING_SERVICE = name -> "Hi, " + name + "!";

    record MapInstanceProvider(Map<String, Object> instances) implements InstanceProvider {

        @Override
        public Object getInstance(final String name) {
            final Object instance = instances.get(name);
            if (instance == null) {
                throw new NoSuchElementException("No instance named " + name);
            }
            return instance;
        }

        @Override
        public <T> T getInstance(final Class<T> type) {
            return instances.values().stream()
                .filter(type::isInstance)
                .map(type::cast)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No instance of " + type.getName()));
        }
    }

    ServiceLocator serviceLocator = ServiceLocator.getInstance();

    @BeforeEach
    void setUp() {
        serviceLocator.setInstanceProvider(new MapInstanceProvider(Map.of("greetingService", GREETING_SERVICE)));
    }

    @Test
    void byClassTyped() {
        final ExampleGreetingService greetingService = serviceLocator.get(ExampleGreetingService.class);

        assertThat(greetingService).isSameAs(GREETING_SERVICE);
    }

    @Test
    void byName() {
        assertThat(serviceLocator.get("greetingService")).isSameAs(GREETING_SERVICE);
    }

    @Test
    void byNameTyped() {
        final ExampleGreetingService greetingService = serviceLocator.get("greetingService", ExampleGreetingService.class);

        assertThat(greetingService.greet("Mari")).isEqualTo("Hi, Mari!");
    }

    @Test
    void notFound() {
        assertThatThrownBy(() -> serviceLocator.get("unknown")).isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(() -> serviceLocator.get(Runnable.class)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void lowerLevelServiceUsesUpperLevelImplementation() {
        assertThat(ExampleBusinessLogicService.getInstance().bar("Mari")).isEqualTo("Hi, Mari!");
    }

    @Test
    void singleton() {
        assertThat(ServiceLocator.getInstance()).isSameAs(serviceLocator);
        assertThat(serviceLocator.hasInstanceProvider()).isTrue();
    }
}
