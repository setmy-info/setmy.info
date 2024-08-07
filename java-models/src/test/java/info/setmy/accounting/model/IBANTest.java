package info.setmy.accounting.model;

import info.setmy.models.accounting.IBAN;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class IBANTest {

    IBAN iban;

    static final String IBAN_CODE_STRING = "FI2112345600000785";

    @Test
    public void parstingAndToString() {
        iban = new IBAN(IBAN_CODE_STRING);
        assertThat(iban.toString()).isEqualTo(IBAN_CODE_STRING);
    }
}
