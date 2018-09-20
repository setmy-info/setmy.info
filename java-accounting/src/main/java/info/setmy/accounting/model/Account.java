package info.setmy.accounting.model;

import info.setmy.models.NamedEntity;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Account extends NamedEntity {

    private Money debit;

    private Money credit;

    private AccountType type;

    public Money getDebit() {
        return debit;
    }

    public void setDebit(Money debit) {
        this.debit = debit;
    }

    public Money getCredit() {
        return credit;
    }

    public void setCredit(Money credit) {
        this.credit = credit;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
}
