package info.setmy.accounting.poc;

import info.setmy.exceptions.LengthValidationException;
import java.util.regex.Pattern;
import info.setmy.exceptions.ValidationException;

/**
 *
 * AAAABBCCDDD form
 *
 * https://www.bankswiftcode.org
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BICCode {

    private static final Pattern LETTERS_PATTER = Pattern.compile("^[A-Za-z]+$");
    private static final Pattern LETTERS_AND_DIGITS_PATTER = Pattern.compile("^[A-Za-z0-9]+$");

    private static final String BRANCH_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS = "Branch code contains un acceptable characters";
    private static final String LOCATION_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS = "Location code contains un acceptable characters";
    private static final String COUNTRY_CODE_CONTAINS_UN_ACCEPTABLE_CHARATERS = "Country code contains un acceptable characters";
    private static final String BANK_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS = "Bank code contains un acceptable characters";

    private String original;

    // 4 characters - bank code
    private String bankCode;

    // ISO-3166-1 alpha-2 country code
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

    public final void parse(final String bicCodeString) {
        validateLenght(bicCodeString, 11);
        final String bankCodePart = bicCodeString.substring(0, 4);
        final String countryCodePart = bicCodeString.substring(4, 6);
        final String locationCodePart = bicCodeString.substring(6, 8);
        final String branchCodePart = bicCodeString.substring(8, 11);
        validateBankCode(bankCodePart);
        validateCountryCode(countryCodePart);
        validateLocationCode(locationCodePart);
        validateBranchCode(branchCodePart);
        original = bicCodeString;
        bankCode = bankCodePart;
        countryCode = countryCodePart;
        locationCode = locationCodePart;
        branchCode = branchCodePart;
    }

    private void validateLenght(final String str, int len) {
        if (str.length() != len) {
            throw new LengthValidationException("Length is not correct");
        }
    }

    private void validateBranchCode(final String branchCodePart) {
        validateLenght(branchCodePart, 3);
        if (!validateLettersAndDigits(branchCodePart)) {
            throw new ValidationException(BRANCH_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS);
        }
    }

    private void validateLocationCode(final String locationCodePart) {
        validateLenght(locationCodePart, 2);
        if (!validateLettersAndDigits(locationCodePart)) {
            throw new ValidationException(LOCATION_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS);
        }
    }

    private void validateCountryCode(final String countryCodePart) {
        validateLenght(countryCodePart, 2);
        if (!validateLetters(countryCodePart)) {
            throw new ValidationException(COUNTRY_CODE_CONTAINS_UN_ACCEPTABLE_CHARATERS);
        }
    }

    private void validateBankCode(final String bankCodePart) {
        validateLenght(bankCodePart, 4);
        if (!validateLetters(bankCodePart)) {
            throw new ValidationException(BANK_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS);
        }
    }

    private boolean validateLetters(final String str) {
        return LETTERS_PATTER.matcher(str).matches();
    }

    private boolean validateLettersAndDigits(final String str) {
        return LETTERS_AND_DIGITS_PATTER.matcher(str).matches();
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        validateBankCode(bankCode);
        original = null;
        this.bankCode = bankCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        validateCountryCode(countryCode);
        original = null;
        this.countryCode = countryCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        validateLocationCode(locationCode);
        original = null;
        this.locationCode = locationCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        validateBranchCode(branchCode);
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
