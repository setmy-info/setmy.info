package info.setmy.models.accounting.balance;

import info.setmy.models.Currency;
import info.setmy.models.Organization;
import static info.setmy.models.accounting.balance.BalanceSheetTerms.ASSETS;
import static info.setmy.models.accounting.balance.BalanceSheetTerms.LIABILITIES;
import java.time.LocalDateTime;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class BalanceSheet {

    private final Organization organization;

    private final LocalDateTime date;

    private final Currency currency;

    private final BalanceSheetSectionItem assets = new BalanceSheetSectionItem(ASSETS);

    private final BalanceSheetSectionItem liabilities = new BalanceSheetSectionItem(LIABILITIES);

    public BalanceSheet(final Organization organization, final LocalDateTime localDateTime, final Currency currency) {
        this.organization = organization;
        this.date = localDateTime;
        this.currency = currency;
    }

    public Organization getOrganization() {
        return organization;
    }

    public BalanceSheetSectionItem getAssets() {
        return assets;
    }

    public BalanceSheetSectionItem getLiabilities() {
        return liabilities;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Currency getCurrency() {
        return currency;
    }
}
