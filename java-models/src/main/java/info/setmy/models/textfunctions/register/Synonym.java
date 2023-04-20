package info.setmy.models.textfunctions.register;

import info.setmy.models.Pair;

import java.util.List;

class Synonym extends Pair<List<String>, String> {

    public Synonym(final List<String> first, final String second) {
        super(first, second);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof String string) {
            return getFirst().contains(string);
        }
        return false;
    }
}
