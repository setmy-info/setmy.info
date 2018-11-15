package info.setmy.accounting.model;

import info.setmy.exceptions.LengthValidationException;
import info.setmy.exceptions.ValidationException;
import java.util.regex.Pattern;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Validation {

    private static final Pattern LETTERS_PATTER = Pattern.compile("^[A-Za-z]+$");
    private static final Pattern LETTERS_AND_DIGITS_PATTER = Pattern.compile("^[A-Za-z0-9]+$");
    private static final Pattern DIGITS_PATTER = Pattern.compile("^[0-9]+$");

    private static final String COUNTRY_CODE_CONTAINS_UN_ACCEPTABLE_CHARATERS = "Country code contains unacceptable characters";
    private static final String BRANCH_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS = "Branch code contains unacceptable characters";
    private static final String LOCATION_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS = "Location code contains unacceptable characters";
    private static final String BANK_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS = "Bank code contains unacceptable characters";
    private static final String IBAN_CHECK_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS = "IBAN contains unacceptable check code characters";
    private static final String IBAN_BBAN_CONTAINS_UN_ACCEPTABLE_CHARACTERS = "IBAN BBAN contains unacceptable check characters";

    public static final Validation validation = newInstance();

    public static Validation newInstance() {
        return new Validation();
    }

    public void validateLenght(final String str, int len) {
        if (str.length() != len) {
            throw new LengthValidationException("Length is not correct");
        }
    }

    public void validateMaxLenght(final String str, int len) {
        if (str.length() > len) {
            throw new LengthValidationException("Length is not correct and is more than expected");
        }
    }

    public boolean validateLetters(final String str) {
        return LETTERS_PATTER.matcher(str).matches();
    }

    public boolean validateLettersAndDigits(final String str) {
        return LETTERS_AND_DIGITS_PATTER.matcher(str).matches();
    }

    public boolean validateDigits(final String str) {
        return DIGITS_PATTER.matcher(str).matches();
    }

    public void validateCountryCode(final String countryCodePart) {
        validation.validateLenght(countryCodePart, 2);
        if (!validation.validateLetters(countryCodePart)) {
            throw new ValidationException(COUNTRY_CODE_CONTAINS_UN_ACCEPTABLE_CHARATERS);
        }
    }

    public void validateBranchCode(final String branchCodePart) {
        validation.validateLenght(branchCodePart, 3);
        if (!validation.validateLettersAndDigits(branchCodePart)) {
            throw new ValidationException(BRANCH_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS);
        }
    }

    public void validateLocationCode(final String locationCodePart) {
        validation.validateLenght(locationCodePart, 2);
        if (!validation.validateLettersAndDigits(locationCodePart)) {
            throw new ValidationException(LOCATION_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS);
        }
    }

    public void validateBankCode(final String bankCodePart) {
        validation.validateLenght(bankCodePart, 4);
        if (!validation.validateLetters(bankCodePart)) {
            throw new ValidationException(BANK_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS);
        }
    }

    public void validateIBANCheckDigits(final String checkDigitsPart) {
        validation.validateLenght(checkDigitsPart, 2);
        if (!validation.validateDigits(checkDigitsPart)) {
            throw new ValidationException(IBAN_CHECK_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS);
        }
    }

    public void validateBBANCode(final String bbanPart) {
        validation.validateMaxLenght(bbanPart, 30);
        if (!validation.validateLettersAndDigits(bbanPart)) {
            throw new ValidationException(IBAN_BBAN_CONTAINS_UN_ACCEPTABLE_CHARACTERS);
        }
    }
}
