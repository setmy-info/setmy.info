package info.setmy.locator;

public interface InstanceProvider {

    Object getInstance(String name);

    <T> T getInstance(Class<T> type);

    default <T> T getInstance(final String name, final Class<T> type) {
        return type.cast(getInstance(name));
    }
}
