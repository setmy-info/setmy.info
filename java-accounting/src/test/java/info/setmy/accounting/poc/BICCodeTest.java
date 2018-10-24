package info.setmy.accounting.poc;

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
        assertThat(bicCode.toString(), is(equalTo("xx")));
    }
}
