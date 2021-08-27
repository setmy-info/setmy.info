package info.setmy.models.accounting.balance;

import java.time.LocalDate;
import java.util.Currency;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BalanceSheet {

    private final String organizationName;

    private final LocalDate date;

    private final BalanceSheetBody balanceSheetBody = new BalanceSheetBody();

    private final Currency currency;

    public BalanceSheet(final String organizationName, final LocalDate date, final Currency currency) {
        this.organizationName = organizationName;
        this.date = date;
        this.currency = currency;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public LocalDate getDate() {
        return date;
    }

    public BalanceSheetBody getBalanceSheetBody() {
        return balanceSheetBody;
    }

    public Currency getCurrency() {
        return currency;
    }
}
