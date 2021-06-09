package info.setmy.accouning.balance;

import java.math.BigDecimal;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BalanceSheetValueItem extends BalanceSheetItem {

    private final BigDecimal value;

    public BalanceSheetValueItem(final String name, final BigDecimal value) {
        super(name);
        this.value = value;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }
}
