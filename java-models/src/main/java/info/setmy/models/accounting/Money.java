package info.setmy.models.accounting;

import info.setmy.models.Currency;
import info.setmy.models.Entity;
import java.math.BigDecimal;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Money extends Entity {

    private BigDecimal amount;

    private Currency currency;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }
}
