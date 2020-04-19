package info.setmy.models;

import info.setmy.exceptions.LengthValidationException;
import static info.setmy.models.accounting.Validation.newInstance;
import java.util.regex.Pattern;

public class Validation {

    private static final Pattern LETTERS_PATTER = Pattern.compile("^[A-Za-z]+$");
    private static final Pattern LETTERS_AND_DIGITS_PATTER = Pattern.compile("^[A-Za-z0-9]+$");
    private static final Pattern DIGITS_PATTER = Pattern.compile("^[0-9]+$");

    public static final Validation VALIDATION = newInstance();

    public static Validation getInstance() {
        return VALIDATION;
    }

    public static Validation newInstance() {
        return new Validation();
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

    public void validateExactLenght(final String str, int len) {
        if (str.length() != len) {
            throw new LengthValidationException("Length is not correct");
        }
    }
}
