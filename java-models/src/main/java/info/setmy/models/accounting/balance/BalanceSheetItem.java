package info.setmy.models.accounting.balance;

import java.math.BigDecimal;
import static java.math.BigDecimal.ZERO;
import java.util.List;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public abstract class BalanceSheetItem {

    private final String name;

    protected BalanceSheetItem(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract BigDecimal getValue();

    public static BigDecimal calculateSum(final List<BalanceSheetItem> balanceSheetItems) {
        return balanceSheetItems.stream().map(item -> item.getValue()).reduce(ZERO, BigDecimal::add);
    }
}
