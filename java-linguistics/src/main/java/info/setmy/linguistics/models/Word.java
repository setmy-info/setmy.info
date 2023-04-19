package info.setmy.linguistics.models;

import java.util.Objects;

import static java.util.Objects.hash;

public class Word {

    private final String value;

    public Word(final String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final Word otherWord = (Word) obj;
        return Objects.equals(value, otherWord.value);
    }

    @Override
    public int hashCode() {
        return hash(value);
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
