package info.setmy.accounting.model;

import info.setmy.accounting.model.IBAN;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class IBANTest {

    IBAN iban;

    static final String IBAN_CODE_STRING = "FI2112345600000785";

    @Test
    public void parstingAndToString() {
        iban = new IBAN(IBAN_CODE_STRING);
        assertThat(iban.toString(), is(equalTo(IBAN_CODE_STRING)));
    }
}
