package info.setmy.locator;

import static java.util.Objects.requireNonNull;

public final class ServiceLocator {

    private static final ServiceLocator INSTANCE = new ServiceLocator();

    private volatile InstanceProvider instanceProvider;

    private ServiceLocator() {
    }

    public static ServiceLocator getInstance() {
        return INSTANCE;
    }

    public void setInstanceProvider(final InstanceProvider instanceProvider) {
        this.instanceProvider = requireNonNull(instanceProvider, "instanceProvider");
    }

    public boolean hasInstanceProvider() {
        return instanceProvider != null;
    }

    public <T> T get(final Class<T> type) {
        return instanceProvider().getInstance(type);
    }

    public Object get(final String name) {
        return instanceProvider().getInstance(name);
    }

    public <T> T get(final String name, final Class<T> type) {
        return instanceProvider().getInstance(name, type);
    }

    private InstanceProvider instanceProvider() {
        final InstanceProvider provider = instanceProvider;
        if (provider == null) {
            throw new IllegalStateException("No InstanceProvider set: the upper level (for example a Spring Boot "
                + "starter) sets it with ServiceLocator.getInstance().setInstanceProvider()");
        }
        return provider;
    }
}
