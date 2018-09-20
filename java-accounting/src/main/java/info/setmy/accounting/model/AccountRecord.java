package info.setmy.accounting.model;

import info.setmy.models.Entity;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class AccountRecord extends Entity {

    private Account account;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
