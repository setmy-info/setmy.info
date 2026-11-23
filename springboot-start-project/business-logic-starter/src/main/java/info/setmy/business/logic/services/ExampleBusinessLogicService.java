package info.setmy.business.logic.services;

import info.setmy.locator.ServiceLocator;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

/**
 * Example of business logic as plain Java: a singleton, created the Java native way, without a DI/IoC container.
 * <p>
 * The instance is created when the class is loaded, the JVM makes it thread-safe. A container (for example the
 * Spring Boot starter) does not create its own instance, it takes this one: {@link #getInstance()}.
 */
@NoArgsConstructor(access = PRIVATE)
public final class ExampleBusinessLogicService {

    private static final ExampleBusinessLogicService INSTANCE = new ExampleBusinessLogicService();

    public static ExampleBusinessLogicService getInstance() {
        return INSTANCE;
    }

    public String foo() {
        return "Hello from foo";
    }

    public String bar(final String name) {
        return ServiceLocator.getInstance().get(ExampleGreetingService.class).greet(name);
    }
}
