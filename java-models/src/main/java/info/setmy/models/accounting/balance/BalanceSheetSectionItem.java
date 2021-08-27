package info.setmy.models.accounting.balance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BalanceSheetSectionItem extends BalanceSheetItem {

    private final List<BalanceSheetItem> balanceSheetItems = new ArrayList<>();

    public BalanceSheetSectionItem(final String name) {
        super(name);
    }

    @Override
    public BigDecimal getValue() {
        return BalanceSheetItem.calculateSum(balanceSheetItems);
    }

    public List<BalanceSheetItem> getBalanceSheetItems() {
        return balanceSheetItems;
    }

    public boolean add(final BalanceSheetItem balanceSheetItem) {
        return balanceSheetItems.add(balanceSheetItem);
    }
}
