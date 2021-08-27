package info.setmy.models.accounting.balance;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BalanceSheetBody {

    private final BalanceSheetSide assets = new BalanceSheetSide();

    private final BalanceSheetSide liabilities = new BalanceSheetSide();

    public BalanceSheetSide getAssets() {
        return assets;
    }

    public BalanceSheetSide getLiabilities() {
        return liabilities;
    }
}
