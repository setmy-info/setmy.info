package info.setmy.accounting.poc;

import info.setmy.exceptions.ParsingException;
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
        assertThat(bicCode.buildString(), is(equalTo(BIC_CODE_STRING)));
    }

    /*Test
    public void parstingAndToString2() {
        bicCode = new BICCode();
        bicCode.setBankCode(BIC_CODE_STRING);
        bicCode.setCountryCode(BIC_CODE_STRING);
        bicCode.setLocationCode(BIC_CODE_STRING);
        bicCode.setBranchCode(BIC_CODE_STRING);
        assertThat(bicCode.toString(), is(equalTo(BIC_CODE_STRING)));
        assertThat(bicCode.buildString(), is(equalTo(BIC_CODE_STRING)));
    }*/

    @Test(expected = ParsingException.class)
    public void wrongBankCode() {
        bicCode = new BICCode();
        bicCode.setBankCode("1234");
    }

    @Test(expected = ParsingException.class)
    public void wrongLocationCode() {
        bicCode = new BICCode();
        bicCode.setCountryCode("12");
    }
}
