package info.setmy.accouning.balance;

import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BalanceSheetValueItemTest {

    private BalanceSheetValueItem balanceSheetValueItem;
    private static final String ITEM_NAME = "Item name";

    @BeforeEach
    public void before() {
        balanceSheetValueItem = new BalanceSheetValueItem(ITEM_NAME, ZERO);
    }

    @Test
    public void initialState() {
        assertThat(balanceSheetValueItem.getName()).isEqualTo(ITEM_NAME);
        assertThat(balanceSheetValueItem.getValue()).isEqualTo(ZERO);
    }
}
