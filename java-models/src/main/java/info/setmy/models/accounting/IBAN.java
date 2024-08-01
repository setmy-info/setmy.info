package info.setmy.models.accounting;

import static info.setmy.models.accounting.Validation.validation;

/**
 * https://pangaliit.ee/arveldused/iban
 *
 * https://www.iban.com/glossary
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class IBAN {

    private String original;

    // 2 characters - ISO-3166-1 alpha-2 country code
    private String countryCode;

    // 2 characters
    private String checkDigits;

    // up to 30 alphanumeric country specific, where 2 firstcharacters are banc code
    private String bban;

    public IBAN() {
    }

    public IBAN(final String ibanString) {
        parse(ibanString);
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(final String original) {
        parse(original);
    }

    public final void parse(final String ibanString) {
        validation.validateMaxLenght(ibanString, 34);//Cant be more than that
        final String countryCodePart = ibanString.substring(0, 2);
        final String checkDigitsPart = ibanString.substring(2, 4);
        final String bbanPart = ibanString.substring(4, ibanString.length());
        validation.validateCountryCode(countryCodePart);
        validation.validateIBANCheckDigits(checkDigitsPart);
        validation.validateBBANCode(bbanPart);
        countryCode = countryCodePart;
        checkDigits = checkDigitsPart;
        bban = bbanPart;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(final String countryCode) {
        validation.validateCountryCode(countryCode);
        original = null;
        this.countryCode = countryCode;
    }

    public String getCheckDigits() {
        return checkDigits;
    }

    public void setCheckDigits(final String checkDigits) {
        validation.validateIBANCheckDigits(checkDigits);
        original = null;
        this.checkDigits = checkDigits;
    }

    public String getBban() {
        return bban;
    }

    public void setBban(final String bban) {
        validation.validateBBANCode(bban);
        original = null;
        this.bban = bban;
    }

    private void buildOriginal() {
        final StringBuffer stringBuilder = new StringBuffer();
        stringBuilder.
                append(countryCode).
                append(checkDigits).
                append(bban);
        original = stringBuilder.toString();
    }

    @Override
    public String toString() {
        if (original == null) {
            buildOriginal();
        }
        return original;
    }
}
