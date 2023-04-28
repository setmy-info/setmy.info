package info.setmy.models;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;

public class RangeIterator<T extends Number> implements Iterable<T>, Iterator<T> {

    private final Range<T> range;

    private final Consumer<RangeIterator<T>> initConsumer;

    private final Function<RangeIterator<T>, T> nextFunction;

    private final Function<RangeIterator<T>, Boolean> hasNextFunction;

    private T current;

    private T step;

    public static RangeIterator<Integer> integerRange(final int to) {
        return integerRange(0, to, 1);
    }

    public static RangeIterator<Integer> integerRange(final int from, final int to) {
        return integerRange(from, to, 1);
    }

    private static RangeIterator<Integer> integerRange(final int from, final int to, final int step) {
        return new RangeIterator<>(
            new Range<>(from, to),
            (rangeIterator) -> {// init
                rangeIterator.setCurrent(from);
                rangeIterator.setStep(step);
            },
            (rangeIterator) -> {// next
                final Integer current = rangeIterator.getCurrent();
                rangeIterator.setCurrent(
                    current + rangeIterator.getStep()
                );
                return current;
            },
            // hasNext
            (rangeIterator) -> rangeIterator.getCurrent() < rangeIterator.getRange().getTo()
        );
    }

    public RangeIterator(
        final Range<T> range,
        final Consumer<RangeIterator<T>> initConsumer,
        final Function<RangeIterator<T>, T> nextFunction,
        final Function<RangeIterator<T>, Boolean> hasNextFunction
    ) {
        this.range = range;
        this.initConsumer = initConsumer;
        this.nextFunction = nextFunction;
        this.hasNextFunction = hasNextFunction;
        init();
    }

    public RangeIterator<T> init() {
        this.initConsumer.accept(this);
        return this;
    }

    public T getCurrent() {
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    public Range<T> getRange() {
        return range;
    }

    public T getStep() {
        return step;
    }

    public void setStep(T step) {
        this.step = step;
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }

    @Override
    public boolean hasNext() {
        return this.hasNextFunction.apply(this);
    }

    @Override
    public T next() {
        return this.nextFunction.apply(this);
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {
        Iterator.super.forEachRemaining(action);
    }
}
