package info.setmy.accouning;

import java.math.BigDecimal;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class Account {

    private final BigDecimal debit;

    private final BigDecimal credit;

    private final String name;

    public Account(final BigDecimal debit, final BigDecimal credit, final String name) {
        this.debit = debit;
        this.credit = credit;
        this.name = name;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public String getName() {
        return name;
    }
}
