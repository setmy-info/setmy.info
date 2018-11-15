package info.setmy.models.accounting;

import info.setmy.models.Bank;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Seller {

    private final List<BankAccount> bankAccounts = new ArrayList<>();
    // TODO : recipient data: names, contacts data: address, phone, email
    // TODO : seller data:

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
}
