package info.setmy.models;

public class Range<T> {

    private final T from;

    private final T to;

    public Range(final T from, final T to) {
        this.from = from;
        this.to = to;
    }

    public T getFrom() {
        return from;
    }

    public T getTo() {
        return to;
    }
}
