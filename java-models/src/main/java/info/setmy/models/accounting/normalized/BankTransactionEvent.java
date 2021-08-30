package info.setmy.models.accounting.normalized;

import info.setmy.models.accounting.BankAccount;
import info.setmy.models.accounting.Money;

/**
 * Babnk transaction event.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BankTransactionEvent extends Event {

    private BankAccount from;

    private BankAccount to;

    private final Money money = new Money();

    public Money getMoney() {
        return money;
    }

    public BankAccount getFrom() {
        return from;
    }

    public void setFrom(BankAccount from) {
        this.from = from;
    }

    public BankAccount getTo() {
        return to;
    }

    public void setTo(BankAccount to) {
        this.to = to;
    }
}
