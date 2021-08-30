package info.setmy.models.accounting.normalized;

import info.setmy.models.accounting.Money;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class TransactionEvent extends Event {

    private String from;

    private String to;

    private final Money money = new Money();

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Money getMoney() {
        return money;
    }
}
