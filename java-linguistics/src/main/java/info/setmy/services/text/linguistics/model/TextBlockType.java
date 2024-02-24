package info.setmy.services.text.linguistics.model;

/**
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public enum TextBlockType {
    UNKNOWN(-1),
    WORD(0),//Reservicng types space: 0 =< type <= 999
    // simple sentence, exclamatory sentence, interrogative sentence sentence
    SENTENCE(1000),//Reservicng types space: 1000 =< type <= 1999 - .!?
    BLOCK(2000),//Reservicng types space: 2000 =< type <= 2999
    ROUND_BRACKETS_BLOCK(2001),//()
    CURLY_BRACKETS_BLOCK(2002),//{}
    SQUARE_BRACKETS_BLOCK(2003),//[]
    ANGLE_BRACKETS_BLOCK(2004),//<>
    QUOTE(3000),//Reservicng types space: 3000 =< type <= 3999
    APOSTROPHE_QUOTE(3001),//'text'
    QUOTATION_QUOTE(3002),//"text"
    GRAVE_ACCENT_QUOTE(3003),//`text`
    ACUTE_ACCENT_QUOTE(3004),//´text´
    ANGULAR_QUOTE(3005),//angular quotation - «text»
    CURVED_APOSTROPHE_QUOTE(3006),//curved apostrophe - ‘text’
    DOUBLE_CURVED_APOSTROPHE_QUOTE(3007),//Double curved apostrophe - “text”
    DIAGONAL_CURVED_QUOTATION_QUOTE(3008),//diagonal curved quotation marks - „text”
    CLAUSE(4000);//Reservicng types space: 4000 =< type <= 4999 - .;:-

    private final int value;

    private TextBlockType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean is(final TextBlockType textBlockType) {
        return is(textBlockType.value);
    }

    public boolean is(final int value) {
        return toSimplify(value) == this;
    }

    public TextBlockType toSimplify(final int value) {//For higher perfocmanse no calculations here, only hard codings
        if (value >= 0 && value <= 999) {
            return WORD;
        } else if (value >= 1000 && value <= 1999) {
            return SENTENCE;
        } else if (value >= 2000 && value <= 2999) {
            return BLOCK;
        } else if (value >= 3000 && value <= 3999) {
            return QUOTE;
        } else if (value >= 4000 && value <= 4999) {
            return CLAUSE;
        } else {
            return UNKNOWN;
        }
    }
}
