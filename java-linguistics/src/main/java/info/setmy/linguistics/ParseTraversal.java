package info.setmy.linguistics;

import info.setmy.linguistics.models.token.Token;
import java.util.ArrayList;
import java.util.List;

public class ParseTraversal {

    private StringBuilder wordTokenBuilder;

    private final List<Token> parsedTokens = new ArrayList<>();

    public StringBuilder getTokenBuilder() {
        return wordTokenBuilder;
    }

    public ParseTraversal setTokenBuilder(final StringBuilder wordBuilder) {
        this.wordTokenBuilder = wordBuilder;
        return this;
    }

    public ParseTraversal setTokenBuilder() {
        this.wordTokenBuilder = new StringBuilder();
        return this;
    }

    public void append(final String value) {
        wordTokenBuilder.append(value);
    }
}
