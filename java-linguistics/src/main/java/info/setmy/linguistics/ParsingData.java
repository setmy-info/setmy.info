package info.setmy.linguistics;

import info.setmy.linguistics.models.token.Token;
import info.setmy.linguistics.models.token.WordToken;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class ParsingData {

    private List<Token> parsedTokens = new ArrayList<>();

    private StringBuilder alphanumericsCollector = new StringBuilder();

    public void addWordTokenAndMakeNewCollector() {
        if (alphanumericsCollector.length() > 0) {
            parsedTokens.add(new WordToken(alphanumericsCollector.toString()));
            alphanumericsCollector = new StringBuilder();
        }
    }

    public boolean add(final Token token) {
        return parsedTokens.add(token);
    }
}
