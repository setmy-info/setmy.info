package info.setmy.linguistics.models.token;

public abstract class AlphaNumericCharacterToken extends TextualToken {

    public AlphaNumericCharacterToken(char value) {
        super(value);
    }

    public AlphaNumericCharacterToken(String value) {
        super(value);
    }
}
