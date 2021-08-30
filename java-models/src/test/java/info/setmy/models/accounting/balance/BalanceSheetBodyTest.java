package info.setmy.models.accounting.balance;

import info.setmy.models.accounting.balance.BalanceSheetBody;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BalanceSheetBodyTest {

    BalanceSheetBody balanceSheetBody;

    @BeforeEach
    public void before() {
        balanceSheetBody = new BalanceSheetBody();
    }

    @Test
    public void initialState() {
        final BalanceSheetBody anotherBalanceSheetBody = new BalanceSheetBody();

        assertThat(balanceSheetBody.getAssets()).isNotNull();
        assertThat(balanceSheetBody.getLiabilities()).isNotNull();

        assertThat(balanceSheetBody.getAssets().size()).isEqualTo(0);
        assertThat(balanceSheetBody.getLiabilities().size()).isEqualTo(0);

        assertThat(balanceSheetBody.getAssets().calculateSum()).isEqualByComparingTo(ZERO);
        assertThat(balanceSheetBody.getLiabilities().calculateSum()).isEqualByComparingTo(ZERO);

        assertThat(balanceSheetBody.getAssets()).isNotSameAs(anotherBalanceSheetBody.getAssets());
        assertThat(balanceSheetBody.getLiabilities()).isNotSameAs(anotherBalanceSheetBody.getLiabilities());
    }
}
