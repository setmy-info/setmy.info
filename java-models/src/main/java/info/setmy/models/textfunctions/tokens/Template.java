package info.setmy.models.textfunctions.tokens;

import info.setmy.models.TripleCursor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static info.setmy.models.textfunctions.tokens.TokenType.PLACEHOLDER;
import static info.setmy.models.textfunctions.tokens.TokenType.USER_TEXT;
import static java.util.Objects.hash;
import static java.util.Objects.nonNull;

public class Template {

    public static final char PLACEHOLDER_BEGIN_CHAR = '{';
    public static final char PLACEHOLDER_END_CHAR = '}';

    private final String templateString;

    private final char[] characters;

    private final List<Token> tokenList = new ArrayList<>();

    private TokenBuilder tokenBuilder;

    public Template(final String templateString) {
        this.templateString = templateString;
        this.characters = templateString.toCharArray();
    }

    public Template tokenize() {
        for (int i = 0; i < characters.length; i++) {
            handleCharacter(characters[i]);
        }
        addCurrentTokenBuilder();
        return this;
    }

    private void handleCharacter(final char character) {
        switch (character) {
            case PLACEHOLDER_BEGIN_CHAR:
                handleBeginChar(character);
                break;
            case PLACEHOLDER_END_CHAR:
                handleEndChar(character);
                break;
            default:
                handleTextChar(character);
        }
    }

    private void handleTextChar(final char character) {
        if (tokenBuilder == null) {
            tokenBuilder = new TokenBuilder(USER_TEXT);
        }
        tokenBuilder.add(character);
    }

    private void handleBeginChar(final char character) {
        addCurrentTokenBuilder();
        tokenBuilder = new TokenBuilder(PLACEHOLDER)
            .add(character);
    }

    private void handleEndChar(final char character) {
        tokenBuilder.add(character);
        addCurrentTokenBuilder();
        tokenBuilder = null;
    }

    private void addCurrentTokenBuilder() {
        if (nonNull(tokenBuilder)) {
            add(new Token(tokenBuilder.getTokenType(), tokenBuilder.toString()));
        }
    }

    public boolean add(final Token token) {
        return tokenList.add(token);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o instanceof String string) {
            return templateString.equals(string);
        }
        if (o instanceof Template template) {
            return Objects.equals(templateString, template.templateString);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return hash(tokenList);
    }

    public List<Token> parse(final String text) {
        final List<Token> result = new ArrayList<>();
        final TripleCursor<Token> tripleCursor = new TripleCursor<>(tokenList);
        final Data data = new Data(
            tripleCursor,
            result,
            text
        );
        while (tripleCursor.hasNext()) {
            handle(data);
            tripleCursor.next();
        }
        return result;
    }

    private void handle(final Data data) {
        switch (data.tripleCursor.getOptionalCurrent().get().getTokenType()) {
            case USER_TEXT:
                handleUserText(data);
                break;
            case PLACEHOLDER:
                handlePlaceHolder(data);
                break;
        }
    }

    private void handleUserText(final Data data) {
        final TripleCursor<Token> tripleCursor = data.tripleCursor;
        final Token current = tripleCursor.getOptionalCurrent().get();
        data.begin = data.begin + current.getValue().length();
    }

    private void handlePlaceHolder(final Data data) {
        final TripleCursor<Token> tripleCursor = data.tripleCursor;
        final Token current = tripleCursor.getOptionalCurrent().get();
        final int nextIndex = nextIndex(data);
        data.result.add(
            new Token(
                current.getTokenType(),
                data.text.substring(data.begin, nextIndex(data)))
        );

        data.begin = nextIndex;
    }

    private int nextIndex(final Data data) {
        final TripleCursor<Token> tripleCursor = data.tripleCursor;
        int result;
        if (tripleCursor.getOptionalNext().isPresent()) {
            result = data.text.indexOf(tripleCursor.getOptionalNext().get().getValue());
        } else {
            result = data.text.length();
        }
        return result;
    }

    class Data {

        public final TripleCursor<Token> tripleCursor;
        public final List<Token> result;
        public final String text;
        public int begin;

        public Data(final TripleCursor<Token> tripleCursor, List<Token> result, String text) {
            this.tripleCursor = tripleCursor;
            this.result = result;
            this.text = text;
        }
    }
}
