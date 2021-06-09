package info.setmy.accouning.balance;

import static info.setmy.accouning.balance.LocalDateForTests.parseLocalDate;
import java.math.BigDecimal;
import static java.math.BigDecimal.ZERO;
import static java.time.Month.FEBRUARY;
import static java.util.Currency.getInstance;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BalanceSheetTest {

    private BalanceSheet balanceSheet;

    private final static String COMAPNY_NAME = "Example Company";

    @BeforeEach
    public void before() {
        balanceSheet = new BalanceSheet(COMAPNY_NAME, parseLocalDate("15.02.2021"), getInstance("EUR"));
    }

    @Test
    public void initialState() {
        assertThat(balanceSheet.getOrganizationName()).isEqualTo(COMAPNY_NAME);
        assertThat(balanceSheet.getDate().getDayOfMonth()).isEqualTo(15);
        assertThat(balanceSheet.getDate().getMonth()).isEqualTo(FEBRUARY);
        assertThat(balanceSheet.getDate().getYear()).isEqualTo(2021);
        assertThat(balanceSheet.getCurrency().getCurrencyCode()).isEqualTo("EUR");
        assertThat(balanceSheet.getCurrency().getDisplayName()).isEqualTo("euro");
        assertThat(balanceSheet.getCurrency().getNumericCode()).isEqualTo(978);
        assertThat(balanceSheet.getCurrency().getSymbol()).isEqualTo("€");
        assertThat(balanceSheet.getCurrency().getDefaultFractionDigits()).isEqualTo(2);
        assertThat(balanceSheet.getBalanceSheetBody().getAssets().size()).isEqualTo(0);
        assertThat(balanceSheet.getBalanceSheetBody().getAssets().calculateSum()).isEqualByComparingTo(ZERO);
        assertThat(balanceSheet.getBalanceSheetBody().getAssets().compareTo(balanceSheet.getBalanceSheetBody().getLiabilities())).isEqualTo(0);
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
        balanceSheet.getBalanceSheetBody().getAssets().add(preFirst);
        balanceSheet.getBalanceSheetBody().getAssets().add(new BalanceSheetValueItem("First", new BigDecimal("123.45")));
        balanceSheet.getBalanceSheetBody().getAssets().add(new BalanceSheetValueItem("Second", new BigDecimal("999.9")));
        balanceSheet.getBalanceSheetBody().getAssets().add(third);
        balanceSheet.getBalanceSheetBody().getAssets().add(new BalanceSheetValueItem("Forth", new BigDecimal("11111.111")));
        balanceSheet.getBalanceSheetBody().getAssets().add(fifth);

        assertThat(balanceSheet.getBalanceSheetBody().getAssets().calculateSum()).isEqualByComparingTo(new BigDecimal("13471.0931"));
        assertThat(balanceSheet.getBalanceSheetBody().getAssets().compareTo(balanceSheet.getBalanceSheetBody().getLiabilities())).isEqualTo(1);
    }

    @Test
    public void compareTo() {
        final BalanceSheetSide assets = balanceSheet.getBalanceSheetBody().getAssets();
        final BalanceSheetSide liabilities = balanceSheet.getBalanceSheetBody().getLiabilities();

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
}
