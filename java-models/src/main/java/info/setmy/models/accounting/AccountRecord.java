package info.setmy.models.accounting;

import info.setmy.models.NamedEntity;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class AccountRecord extends NamedEntity {

    private Money debit;

    private Money credit;

    private Account account;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
