package info.setmy.models.accounting;

import info.setmy.exceptions.LengthValidationException;
import info.setmy.exceptions.ValidationException;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Validation extends info.setmy.models.Validation {

    private static final String COUNTRY_CODE_CONTAINS_UN_ACCEPTABLE_CHARATERS = "Country code contains unacceptable characters";
    private static final String BRANCH_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS = "Branch code contains unacceptable characters";
    private static final String LOCATION_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS = "Location code contains unacceptable characters";
    private static final String BANK_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS = "Bank code contains unacceptable characters";
    private static final String IBAN_CHECK_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS = "IBAN contains unacceptable check code characters";
    private static final String IBAN_BBAN_CONTAINS_UN_ACCEPTABLE_CHARACTERS = "IBAN BBAN contains unacceptable check characters";

    public static final Validation validation = newInstance();

    public static Validation getInstance() {
        return validation;
    }

    public static Validation newInstance() {
        return new Validation();
    }

    public void validateMaxLenght(final String str, int len) {
        if (str.length() > len) {
            throw new LengthValidationException("Length is not correct and is more than expected");
        }
    }

    public void validateCountryCode(final String countryCodePart) {
        validation.validateExactLenght(countryCodePart, 2);
        if (!validation.validateLetters(countryCodePart)) {
            throw new ValidationException(COUNTRY_CODE_CONTAINS_UN_ACCEPTABLE_CHARATERS);
        }
    }

    public void validateBranchCode(final String branchCodePart) {
        validation.validateExactLenght(branchCodePart, 3);
        if (!validation.validateLettersAndDigits(branchCodePart)) {
            throw new ValidationException(BRANCH_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS);
        }
    }

    public void validateLocationCode(final String locationCodePart) {
        validation.validateExactLenght(locationCodePart, 2);
        if (!validation.validateLettersAndDigits(locationCodePart)) {
            throw new ValidationException(LOCATION_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS);
        }
    }

    public void validateBankCode(final String bankCodePart) {
        validation.validateExactLenght(bankCodePart, 4);
        if (!validation.validateLetters(bankCodePart)) {
            throw new ValidationException(BANK_CODE_CONTAINS_UN_ACCEPTABLE_CHARACTERS);
        }
    }

    public void validateIBANCheckDigits(final String checkDigitsPart) {
        validation.validateExactLenght(checkDigitsPart, 2);
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
