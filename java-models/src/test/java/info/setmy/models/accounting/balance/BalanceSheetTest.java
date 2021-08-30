package info.setmy.models.accounting.balance;

import info.setmy.models.Company;
import info.setmy.models.Currency;
import info.setmy.models.Organization;
import static info.setmy.models.accounting.balance.LocalDateForTests.parseLocalDate;
import static java.math.BigDecimal.ZERO;
import static java.time.Month.FEBRUARY;

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
    //private final Locale EE = new Locale("et", "EE");

    @BeforeEach
    public void before() {
        //Locale.setDefault(EE);
        var company = new Company();
        company.setName(COMAPNY_NAME);
        var currency = new Currency();
        currency.setName("EUR");
        balanceSheet = new BalanceSheet((Organization) company, parseLocalDate("15.02.2021 12:15:30"), currency);
    }

    @Test
    public void initialState() {
        var company = new Company();
        company.setName(COMAPNY_NAME);
        var currency = new Currency();
        currency.setName("EUR");
        final BalanceSheet anotherBalanceSheet = new BalanceSheet((Organization) company, parseLocalDate("15.02.2021 12:15:30"), currency);

        assertThat(balanceSheet.getOrganization().getName()).isEqualTo(COMAPNY_NAME);
        assertThat(balanceSheet.getDate().getDayOfMonth()).isEqualTo(15);
        assertThat(balanceSheet.getDate().getMonth()).isEqualTo(FEBRUARY);
        assertThat(balanceSheet.getDate().getYear()).isEqualTo(2021);
        assertThat(balanceSheet.getCurrency().getName()).isEqualTo("EUR");

        assertThat(balanceSheet.getAssets()).isNotNull();
        assertThat(balanceSheet.getLiabilities()).isNotNull();

        assertThat(balanceSheet.getAssets().getBalanceSheetItems().size()).isEqualTo(0);
        assertThat(balanceSheet.getLiabilities().getBalanceSheetItems().size()).isEqualTo(0);

        assertThat(balanceSheet.getAssets().calculateSum()).isEqualByComparingTo(ZERO);
        assertThat(balanceSheet.getLiabilities().calculateSum()).isEqualByComparingTo(ZERO);

        assertThat(balanceSheet.getAssets()).isNotSameAs(anotherBalanceSheet.getAssets());
        assertThat(balanceSheet.getLiabilities()).isNotSameAs(anotherBalanceSheet.getLiabilities());
    }
}
