package info.setmy.microservice.service;

import info.setmy.business.logic.services.ExampleGreetingService;
import org.springframework.stereotype.Service;

/**
 * The application implements the lower level interface {@link ExampleGreetingService}. The plain Java
 * ExampleBusinessLogicService finds it with ServiceLocator.
 */
@Service(ExampleGreetingServiceImpl.BEAN_NAME)
public class ExampleGreetingServiceImpl implements ExampleGreetingService {

    public static final String BEAN_NAME = "exampleGreetingService";

    @Override
    public String greet(final String name) {
        return "Hello, " + name + "! (from Spring)";
    }
}
