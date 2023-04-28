package info.setmy.models;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class TripleCursor<T> {

    private final List<T> list;

    private Optional<T> optionalPrevious = empty();

    private Optional<T> optionalCurrent = empty();

    private Optional<T> optionalNext = empty();

    private int currentIndex;

    public TripleCursor(final List<T> list) {
        this.list = list;
        init();
    }

    public TripleCursor init() {
        if (this.list.size() == 0) {
            return this;
        }
        optionalCurrent = of(this.list.get(currentIndex));
        if (this.list.size() > 1) {
            optionalNext = of(this.list.get(currentIndex + 1));
        }
        return this;
    }

    public boolean hasNext() {
        return optionalNext.isPresent();
    }

    public void next() {
        currentIndex++;
        optionalPrevious = optionalCurrent;
        optionalCurrent = optionalNext;
        optionalNext = list.size() > currentIndex + 1 ? of(list.get(currentIndex + 1)) : empty();
    }

    public Optional<T> getOptionalPrevious() {
        return optionalPrevious;
    }

    public void setOptionalPrevious(Optional<T> optionalPrevious) {
        this.optionalPrevious = optionalPrevious;
    }

    public Optional<T> getOptionalCurrent() {
        return optionalCurrent;
    }

    public void setOptionalCurrent(Optional<T> optionalCurrent) {
        this.optionalCurrent = optionalCurrent;
    }

    public Optional<T> getOptionalNext() {
        return optionalNext;
    }

    public void setOptionalNext(Optional<T> optionalNext) {
        this.optionalNext = optionalNext;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public List<T> getList() {
        return list;
    }
}
