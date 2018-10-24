package info.setmy.accounting.poc;

import java.util.regex.Pattern;
import info.setmy.exceptions.ParsingException;
import java.util.regex.Matcher;

/**
 *
 * AAAABBCCDDD form
 *
 * https://www.bankswiftcode.org
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BICCode {

    private static final Pattern LETTERS_PATTER = Pattern.compile("[A-Za-z]");
    private static final Pattern LETTERS_AND_DIGITS_PATTER = Pattern.compile("[A-Za-z0-9]");

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
        if (bicCodeString.length() != 11) {
            throw new ParsingException("");
        }
        final String bankCodePart = bicCodeString.substring(0, 4);
        final String countryCodePart = bicCodeString.substring(4, 2);
        final String locationCodePart = bicCodeString.substring(6, 2);
        final String branchCodePart = bicCodeString.substring(8, 3);
        
        Matcher m = LETTERS_PATTER.matcher(bankCodePart);
        m = LETTERS_PATTER.matcher(countryCodePart);
        m = LETTERS_AND_DIGITS_PATTER.matcher(locationCodePart);
        m = LETTERS_AND_DIGITS_PATTER.matcher(branchCodePart);
        
        original = bicCodeString;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();

        return sb.toString();
    }
}
