package info.setmy.linguistics;

import info.setmy.linguistics.models.token.TextCharacterToken;
import info.setmy.linguistics.models.token.Token;
import static info.setmy.linguistics.models.token.TokenUtils.toToken;

public class Parser {

    public void parse(final String content) {
        final char[] chars = content.toCharArray();
        var parseTraversal = new ParseTraversal();
        for (char character : chars) {
            parse(toToken(character), parseTraversal);
        }
    }

    private void parse(final Token token, final ParseTraversal parseTraversal) {
        if (token instanceof TextCharacterToken) {
            parseTraversal.append(token.getValue());
        }
    }
}
