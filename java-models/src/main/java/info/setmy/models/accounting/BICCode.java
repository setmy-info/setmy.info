package info.setmy.models.accounting;

import static info.setmy.models.accounting.Validation.validation;

/**
 *
 * AAAABBCCDDD form
 *
 * https://www.bankswiftcode.org
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BICCode {

    private String original;

    // 4 characters - bank code
    private String bankCode;

    // 2 characters - ISO-3166-1 alpha-2 country code
    private String countryCode;

    // 2 characters - location code (letters and digits)
    private String locationCode;

    // 3 characters - branch code
    private String branchCode;

    public BICCode() {
    }

    public BICCode(final String bicCodeString) {
        parse(bicCodeString);
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(final String original) {
        parse(original);
    }

    public final void parse(final String bicCodeString) {
        validation.validateExactLenght(bicCodeString, 11);
        final String bankCodePart = bicCodeString.substring(0, 4);
        final String countryCodePart = bicCodeString.substring(4, 6);
        final String locationCodePart = bicCodeString.substring(6, 8);
        final String branchCodePart = bicCodeString.substring(8, 11);
        validation.validateBankCode(bankCodePart);
        validation.validateCountryCode(countryCodePart);
        validation.validateLocationCode(locationCodePart);
        validation.validateBranchCode(branchCodePart);
        original = bicCodeString;
        bankCode = bankCodePart;
        countryCode = countryCodePart;
        locationCode = locationCodePart;
        branchCode = branchCodePart;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        validation.validateBankCode(bankCode);
        original = null;
        this.bankCode = bankCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        validation.validateCountryCode(countryCode);
        original = null;
        this.countryCode = countryCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        validation.validateLocationCode(locationCode);
        original = null;
        this.locationCode = locationCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        validation.validateBranchCode(branchCode);
        original = null;
        this.branchCode = branchCode;
    }

    private void buildOriginal() {
        final StringBuffer stringBuilder = new StringBuffer();
        stringBuilder.
                append(bankCode).
                append(countryCode).
                append(locationCode).
                append(branchCode);
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
