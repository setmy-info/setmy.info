package info.setmy.models.accounting.balance;

import java.math.BigDecimal;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class BalanceSheetSectionItemTest {

    private BalanceSheetSectionItem balanceSheetSectionItem;

    private final BalanceSheetSectionItem assets = new BalanceSheetSectionItem("assetc");
    private final BalanceSheetSectionItem liabilities = new BalanceSheetSectionItem("liabilities");

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

        assertThat(assets.size()).isEqualTo(0);
        assertThat(assets.calculateSum()).isEqualByComparingTo(ZERO);
        assertThat(assets.compareTo(liabilities)).isEqualTo(0);
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

    @Test
    public void compareTo() {
        assets.add(new BalanceSheetValueItem("First", new BigDecimal("11.11")));
        assets.add(new BalanceSheetValueItem("Second", new BigDecimal("111.111")));
        liabilities.add(new BalanceSheetValueItem("First", new BigDecimal("11.11")));
        liabilities.add(new BalanceSheetValueItem("Second", new BigDecimal("111.111")));
        assertThat(assets.compareTo(liabilities)).isEqualTo(0);
        assertFalse(assets.lessThan(liabilities));
        assertTrue(assets.sameThan(liabilities));
        assertFalse(assets.moreThan(liabilities));
        assets.clear();
        liabilities.clear();

        assets.add(new BalanceSheetValueItem("First", new BigDecimal("11.11")));
        assets.add(new BalanceSheetValueItem("Second", new BigDecimal("111.111")));
        liabilities.add(new BalanceSheetValueItem("First", new BigDecimal("11.11")));
        liabilities.add(new BalanceSheetValueItem("Second", new BigDecimal("222.222")));
        assertThat(assets.compareTo(liabilities)).isEqualTo(-1);
        assertTrue(assets.lessThan(liabilities));
        assertFalse(assets.sameThan(liabilities));
        assertFalse(assets.moreThan(liabilities));
        assets.clear();
        liabilities.clear();

        assets.add(new BalanceSheetValueItem("First", new BigDecimal("11.11")));
        assets.add(new BalanceSheetValueItem("Second", new BigDecimal("222.222")));
        liabilities.add(new BalanceSheetValueItem("First", new BigDecimal("11.11")));
        liabilities.add(new BalanceSheetValueItem("Second", new BigDecimal("111.111")));
        assertThat(assets.compareTo(liabilities)).isEqualTo(1);
        assertFalse(assets.lessThan(liabilities));
        assertFalse(assets.sameThan(liabilities));
        assertTrue(assets.moreThan(liabilities));
        assets.clear();
        liabilities.clear();
    }

    @Test
    public void structureSummation() {
        final BalanceSheetSectionItem third = new BalanceSheetSectionItem("Third");
        final BalanceSheetSectionItem fifth = new BalanceSheetSectionItem("Fifth");
        final BalanceSheetSectionItem preFirst = new BalanceSheetSectionItem("Pre First");
        final BalanceSheetSectionItem underPreFirst = new BalanceSheetSectionItem("Under Pre First");
        preFirst.getBalanceSheetItems().add(underPreFirst);
        preFirst.getBalanceSheetItems().add(new BalanceSheetValueItem("Pre First First", new BigDecimal("1.1")));
        underPreFirst.getBalanceSheetItems().add(new BalanceSheetValueItem("Under Pre First First", new BigDecimal("1.1")));
        third.getBalanceSheetItems().add(new BalanceSheetValueItem("Third First", new BigDecimal("1.1")));
        third.getBalanceSheetItems().add(new BalanceSheetValueItem("Third Second", new BigDecimal("11.11")));
        fifth.getBalanceSheetItems().add(new BalanceSheetValueItem("Fifth First", new BigDecimal("111.111")));
        fifth.getBalanceSheetItems().add(new BalanceSheetValueItem("Fifth Second", new BigDecimal("1111.1111")));
        assets.add(preFirst);
        assets.add(new BalanceSheetValueItem("First", new BigDecimal("123.45")));
        assets.add(new BalanceSheetValueItem("Second", new BigDecimal("999.9")));
        assets.add(third);
        assets.add(new BalanceSheetValueItem("Forth", new BigDecimal("11111.111")));
        assets.add(fifth);

        assertThat(assets.calculateSum()).isEqualByComparingTo(new BigDecimal("13471.0931"));
    }
}
