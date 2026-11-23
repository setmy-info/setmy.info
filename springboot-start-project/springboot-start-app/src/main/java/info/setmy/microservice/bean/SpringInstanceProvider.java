package info.setmy.microservice.bean;

import info.setmy.locator.InstanceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;

/**
 * {@link InstanceProvider} of the application: the instances are the beans of the application context, a name is a
 * bean name. A missing bean throws {@link org.springframework.beans.factory.NoSuchBeanDefinitionException}.
 * <p>
 * The beans are looked up at each call, so a bean created after the start (lazy, prototype) is found too.
 */
@RequiredArgsConstructor
public class SpringInstanceProvider implements InstanceProvider {

    private final ApplicationContext applicationContext;

    @Override
    public Object getInstance(final String name) {
        return applicationContext.getBean(name);
    }

    @Override
    public <T> T getInstance(final Class<T> type) {
        return applicationContext.getBean(type);
    }

    @Override
    public <T> T getInstance(final String name, final Class<T> type) {
        return applicationContext.getBean(name, type);
    }
}
