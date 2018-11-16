package info.setmy.models;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Country extends NamedEntity {

    private String alpha2;

    private String alpha3;

    private int numeric;

    public String getAlpha2() {
        return alpha2;
    }

    public void setAlpha2(final String alpha2) {
        Validation.getInstance().validateExactLenght(alpha2, 2);
        Validation.getInstance().validateLetters(alpha2);
        this.alpha2 = alpha2;
    }

    public String getAlpha3() {
        return alpha3;
    }

    public void setAlpha3(final String alpha3) {
        Validation.getInstance().validateExactLenght(alpha3, 3);
        Validation.getInstance().validateLetters(alpha3);
        this.alpha3 = alpha3;
    }

    public int getNumeric() {
        return numeric;
    }

    public String getNumericString() {
        return String.format("%03d", numeric);
    }

    public void setNumeric(final int numeric) {
        this.numeric = numeric;
    }
}
