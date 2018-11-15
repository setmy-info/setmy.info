package info.setmy.accounting.model;

import java.time.LocalDateTime;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class TransactionEvent {

    private String from;

    private String to;

    private LocalDateTime dateTime;

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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Money getMoney() {
        return money;
    }
}
