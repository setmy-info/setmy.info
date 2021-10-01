package info.setmy.models.accounting;

import info.setmy.models.Bank;
import info.setmy.models.Entity;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BankAccount extends Entity {

    private Bank bank;

    private IBAN iban;

    public BankAccount() {
    }

    public IBAN getIban() {
        return iban;
    }

    public void setIban(IBAN iban) {
        this.iban = iban;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
