package info.setmy.models.accounting.balance;

import info.setmy.models.accounting.balance.BalanceSheetSectionItem;
import info.setmy.models.accounting.balance.BalanceSheetValueItem;
import java.math.BigDecimal;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BalanceSheetSectionItemTest {

    private BalanceSheetSectionItem balanceSheetSectionItem;

    private static final String ITEM_NAME = "Item name";
    private static final String SUB_ITEM_NAME = "Sub-item name";

    @BeforeEach
    public void before() {
        balanceSheetSectionItem = new BalanceSheetSectionItem(ITEM_NAME);
    }

    @Test
    public void initialState() {
        assertThat(balanceSheetSectionItem.getName()).isEqualTo(ITEM_NAME);
        assertThat(balanceSheetSectionItem.getValue()).isEqualTo(ZERO);
    }

    @Test
    public void getValue_forDirectSubItems() {
        balanceSheetSectionItem.add(new BalanceSheetValueItem(SUB_ITEM_NAME, new BigDecimal("111.111")));
        balanceSheetSectionItem.add(new BalanceSheetValueItem(SUB_ITEM_NAME, new BigDecimal("11.11")));

        assertThat(balanceSheetSectionItem.getValue()).isEqualTo(new BigDecimal("122.221"));
    }

    @Test
    public void getValue_forSecondLevelSubItems() {
        final BalanceSheetSectionItem secondBalanceSheetSectionItem = new BalanceSheetSectionItem("secont " + ITEM_NAME);
        secondBalanceSheetSectionItem.add(new BalanceSheetValueItem(SUB_ITEM_NAME, new BigDecimal("111.111")));
        secondBalanceSheetSectionItem.add(new BalanceSheetValueItem(SUB_ITEM_NAME, new BigDecimal("11.11")));
        balanceSheetSectionItem.add(secondBalanceSheetSectionItem);

        assertThat(balanceSheetSectionItem.getValue()).isEqualTo(new BigDecimal("122.221"));
    }

    @Test
    public void getValue_forThirdLevelSubItems() {
        final BalanceSheetSectionItem secondBalanceSheetSectionItem = new BalanceSheetSectionItem("secont " + ITEM_NAME);
        final BalanceSheetSectionItem thirdBalanceSheetSectionItem = new BalanceSheetSectionItem("third " + ITEM_NAME);
        thirdBalanceSheetSectionItem.add(new BalanceSheetValueItem(SUB_ITEM_NAME, new BigDecimal("111.111")));
        thirdBalanceSheetSectionItem.add(new BalanceSheetValueItem(SUB_ITEM_NAME, new BigDecimal("11.11")));
        secondBalanceSheetSectionItem.add(thirdBalanceSheetSectionItem);
        balanceSheetSectionItem.add(secondBalanceSheetSectionItem);

        assertThat(balanceSheetSectionItem.getValue()).isEqualTo(new BigDecimal("122.221"));
    }
}
