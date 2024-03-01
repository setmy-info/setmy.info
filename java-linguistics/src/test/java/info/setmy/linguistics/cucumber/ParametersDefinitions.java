package info.setmy.linguistics.cucumber;

import info.setmy.linguistics.models.token.Token;
import io.cucumber.java.ParameterType;

import java.util.function.Function;

public class ParametersDefinitions {

    @ParameterType(".*")
    public Function<Token, Boolean> isInType(final String type) {
        switch (type) {
            case "WhiteCharToken":
                return this::isWhiteCharToken;
            case "WordToken":
                return this::isWordToken;
            case "SentenceEndingToken":
                return this::isSentenceEndingToken;
            case "PhraseSeparatorToken":
                return this::isPhraseSeparatorToken;
            case "OtherTextualCharacterToken":
                return this::isOtherTextualCharacterToken;
            case "LineEndingToken":
                return this::isLineEndingToken;
            case "SoloQuotationToken":
                return this::isSoloQuotationToken;
            case "BeginBlockToken":
                return this::isBeginBlockToken;
            case "EndBlockToken":
                return this::isEndBlockToken;
            case "BeginPairedQuotationToken":
                return this::isBeginPairedQuotationToken;
            case "EndPairedQuotationToken":
                return this::isEndPairedQuotationToken;
            default:
                return this::isUnknown;
        }
    }

    private Boolean isUnknown(final Token token) {
        return false;
    }

    private Boolean isWhiteCharToken(final Token token) {
        return isIn(token, "WhiteCharToken");
    }

    private Boolean isWordToken(final Token token) {
        return isIn(token, "WordToken");
    }

    private Boolean isSentenceEndingToken(final Token token) {
        return isIn(token, "SentenceEndingToken");
    }

    private Boolean isPhraseSeparatorToken(final Token token) {
        return isIn(token, "PhraseSeparatorToken");
    }

    private Boolean isOtherTextualCharacterToken(final Token token) {
        return isIn(token, "OtherTextualCharacterToken");
    }

    private Boolean isLineEndingToken(final Token token) {
        return isIn(token, "LineEndingToken");
    }

    private Boolean isSoloQuotationToken(final Token token) {
        return isIn(token, "SoloQuotationToken");
    }

    private Boolean isBeginBlockToken(final Token token) {
        return isIn(token, "BeginBlockToken");
    }

    private Boolean isEndBlockToken(final Token token) {
        return isIn(token, "EndBlockToken");
    }

    private Boolean isBeginPairedQuotationToken(final Token token) {
        return isIn(token, "BeginPairedQuotationToken");
    }

    private Boolean isEndPairedQuotationToken(final Token token) {
        return isIn(token, "EndPairedQuotationToken");
    }

    private boolean isIn(final Token token, final String type) {
        return token.getClass().getSimpleName().equals(type);
    }
}
