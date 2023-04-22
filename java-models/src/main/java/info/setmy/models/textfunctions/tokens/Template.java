package info.setmy.models.textfunctions.tokens;

import info.setmy.models.Pair;

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
    public static final int NO_POSITION = -1;

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

    public List<Token> parse(final String parse) {
        final List<Token> result = new ArrayList<>();
        final List<Token> userTokens = getUserTextTokens();
        final List<Pair<Integer, Integer>> userTextTokenPositions = new ArrayList<>();
        int position;
        Pair<Integer, Integer> currentFragment;
        Pair<Integer, Integer> previousFragment = null;
        for (Token userToken : userTokens) {
            final String textPart = userToken.getValue();
            position = parse.indexOf(textPart);
            if (position > NO_POSITION) {
                currentFragment = new Pair<>(position, position + textPart.length()); // fount text begin index and to index;
                if (nonNull(previousFragment)) {//have fragment found
                    if (currentFragment.getFirst() < previousFragment.getSecond()) {
                        break; // No further parsing
                    }
                } else {//no any fragments found
                    userTextTokenPositions.add(currentFragment);
                }
                previousFragment = currentFragment;
            }
        }
        // TODO : find placeholder fragments and make fragments list (and call transform functions ?)
        return result;
    }

    private List<Token> getUserTextTokens() {
        return tokenList.stream()
            .filter(token -> token.getTokenType() == USER_TEXT)
            .toList();
    }
}
