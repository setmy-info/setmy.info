package info.setmy.models.textfunctions.tokens;

import info.setmy.models.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.hash;
import static java.util.Objects.nonNull;

public class Template {

    private final String templateString;

    private final List<Token> templateList = new ArrayList<>();

    public Template(String templateString) {
        this.templateString = templateString;
    }

    public boolean add(final Token token) {
        return templateList.add(token);
    }

    public List<Token> getTemplateList() {
        return templateList;
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
        return hash(templateList);
    }

    private final static int NO_POSITION = -1;

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
        return templateList.stream()
            .filter(token -> token.getTokenType() == TokenType.USER_TEXT)
            .toList();
    }
}
