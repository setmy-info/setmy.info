package info.setmy.models.accounting.balance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
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

    public boolean lessThan(final BalanceSheetSectionItem anotherBalanceSide) {
        return compareTo(anotherBalanceSide) == -1;
    }

    public boolean moreThan(final BalanceSheetSectionItem anotherBalanceSide) {
        return compareTo(anotherBalanceSide) == 1;
    }

    public boolean sameThan(final BalanceSheetSectionItem anotherBalanceSide) {
        return compareTo(anotherBalanceSide) == 0;
    }

    /**
     * @param anotherBalanceSide {@code BalanceSheetSide} to which this
     * {@code BalanceSheetSide} is to be compared.
     * @return -1, 0, or 1 as this {@code BigDecimal} is numerically less than,
     * equal to, or greater than {@code anotherBalanceSide}.
     */
    public int compareTo(final BalanceSheetSectionItem anotherBalanceSide) {
        return calculateSum().compareTo(anotherBalanceSide.calculateSum());
    }

    public BigDecimal calculateSum() {
        return BalanceSheetItem.calculateSum((List<BalanceSheetItem>) balanceSheetItems);
    }

    public int size() {
        return balanceSheetItems.size();
    }

    public void clear() {
        balanceSheetItems.clear();
    }
}
