package info.setmy.accouning.balance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BalanceSheetSide extends ArrayList<BalanceSheetItem> {

    public BigDecimal calculateSum() {
        return BalanceSheetItem.calculateSum((List<BalanceSheetItem>) this);
    }

    /**
     * @param anotherBalanceSide {@code BalanceSheetSide} to which this
     * {@code BalanceSheetSide} is to be compared.
     * @return -1, 0, or 1 as this {@code BigDecimal} is numerically less than,
     * equal to, or greater than {@code anotherBalanceSide}.
     */
    public int compareTo(final BalanceSheetSide anotherBalanceSide) {
        return calculateSum().compareTo(anotherBalanceSide.calculateSum());
    }

    public boolean lessThan(final BalanceSheetSide anotherBalanceSide) {
        return compareTo(anotherBalanceSide) == -1;
    }

    public boolean moreThan(final BalanceSheetSide anotherBalanceSide) {
        return compareTo(anotherBalanceSide) == 1;
    }

    public boolean sameThan(final BalanceSheetSide anotherBalanceSide) {
        return compareTo(anotherBalanceSide) == 0;
    }
}
