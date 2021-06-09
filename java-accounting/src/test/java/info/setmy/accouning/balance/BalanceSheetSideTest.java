package info.setmy.accouning.balance;

import java.math.BigDecimal;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BalanceSheetSideTest {

    final BalanceSheetSide assets = new BalanceSheetSide();
    final BalanceSheetSide liabilities = new BalanceSheetSide();

    @Test
    public void initialState() {
        assertThat(assets.size()).isEqualTo(0);
        assertThat(assets.calculateSum()).isEqualByComparingTo(ZERO);
        assertThat(assets.compareTo(liabilities)).isEqualTo(0);
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
