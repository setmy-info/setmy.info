package info.setmy.microservice.bean;

import info.setmy.locator.InstanceProvider;
import info.setmy.locator.ServiceLocator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The lower level (plain Java) {@link ServiceLocator} gets the beans of the application: implementations of lower
 * level interfaces, made here in the application, are found by the plain Java code.
 */
@Configuration
public class ServiceLocatorBeans {

    @Bean
    public InstanceProvider instanceProvider(final ApplicationContext applicationContext) {
        return new SpringInstanceProvider(applicationContext);
    }

    /**
     * The library singleton with the provider set. A bean that uses ServiceLocator already while it is created
     * injects this bean, so that the provider is set before.
     */
    @Bean
    public ServiceLocator serviceLocator(final InstanceProvider instanceProvider) {
        final ServiceLocator serviceLocator = ServiceLocator.getInstance();
        serviceLocator.setInstanceProvider(instanceProvider);
        return serviceLocator;
    }
}
