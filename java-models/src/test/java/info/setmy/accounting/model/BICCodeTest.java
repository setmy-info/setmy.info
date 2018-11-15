package info.setmy.accounting.model;

import info.setmy.accounting.model.BICCode;
import info.setmy.exceptions.LengthValidationException;
import info.setmy.exceptions.ValidationException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BICCodeTest {

    BICCode bicCode;

    static final String BIC_CODE_STRING = "SPFBFIHHECB";

    @Test
    public void parstingAndToString() {
        bicCode = new BICCode(BIC_CODE_STRING);
        assertThat(bicCode.toString(), is(equalTo(BIC_CODE_STRING)));
    }

    @Test
    public void parstingAndToString2() {
        bicCode = new BICCode();
        bicCode.setBankCode("SPFB");
        bicCode.setCountryCode("FI");
        bicCode.setLocationCode("HH");
        bicCode.setBranchCode("ECB");
        assertThat(bicCode.toString(), is(equalTo(BIC_CODE_STRING)));
    }

    @Test(expected = ValidationException.class)
    public void wrongBankCode() {
        bicCode = new BICCode();
        bicCode.setBankCode("1234");
    }

    @Test(expected = LengthValidationException.class)
    public void wrongLengthBankCode1() {
        bicCode = new BICCode();
        bicCode.setBankCode("SPF");
    }

    @Test(expected = LengthValidationException.class)
    public void wrongLengthBankCode2() {
        bicCode = new BICCode();
        bicCode.setBankCode("SPFBB");
    }

    @Test(expected = ValidationException.class)
    public void wrongCountryCode() {
        bicCode = new BICCode();
        bicCode.setCountryCode("12");
    }

    @Test(expected = LengthValidationException.class)
    public void wrongLengthCountryCode1() {
        bicCode = new BICCode();
        bicCode.setCountryCode("F");
    }

    @Test(expected = LengthValidationException.class)
    public void wrongLengthCountryCode2() {
        bicCode = new BICCode();
        bicCode.setCountryCode("FIM");
    }

    @Test(expected = ValidationException.class)
    public void wrongLocationCode() {
        bicCode = new BICCode();
        bicCode.setLocationCode(".-");
    }

    @Test(expected = LengthValidationException.class)
    public void wrongLengthLocationCode1() {
        bicCode = new BICCode();
        bicCode.setLocationCode("H");
    }

    @Test(expected = LengthValidationException.class)
    public void wrongLengthLocationCode2() {
        bicCode = new BICCode();
        bicCode.setLocationCode("HHH");
    }

    @Test(expected = ValidationException.class)
    public void wrongBranchCode() {
        bicCode = new BICCode();
        bicCode.setBranchCode(".-/");
    }

    @Test(expected = LengthValidationException.class)
    public void wrongLengthBranchCode1() {
        bicCode = new BICCode();
        bicCode.setBranchCode("EC");
    }

    @Test(expected = LengthValidationException.class)
    public void wrongLengthBranchCode2() {
        bicCode = new BICCode();
        bicCode.setBranchCode("ECBC");
    }
}
