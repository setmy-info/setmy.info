package info.setmy.accouning.balance;

import static info.setmy.accouning.balance.LocalDateForTests.parseLocalDate;
import static java.time.Month.FEBRUARY;
import static java.util.Currency.getInstance;
import static org.assertj.core.api.Assertions.assertThat;
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
    }
}
