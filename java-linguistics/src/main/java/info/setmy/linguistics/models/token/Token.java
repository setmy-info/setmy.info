package info.setmy.linguistics.models.token;

import java.util.Objects;
import static java.util.Objects.hash;

public abstract class Token {

    private final String value;

    public Token(final char value) {
        this.value = "" + value;
    }

    public Token(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /*public boolean is(final char chr) {
        return is("" + chr);
    }

    public boolean is(final String str) {
        return equals((Object) str);
    }*/
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Token token) {
            return Objects.equals(value, token.value);
        }
        if (obj.getClass() == String.class) {
            final String str = (String) obj;
            return Objects.equals(value, str);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return hash(value);
    }

    @Override
    public String toString() {
        return value == null ? "" : value;
    }
}
