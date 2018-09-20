package info.setmy.accounting.model;

import info.setmy.models.NamedEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Account extends NamedEntity<Long> {

    private Account parent;

    private final List<Account> subAccounts = new ArrayList<>();

    private AccountType type;

    private int number;

    public List<Account> getSubAccounts() {
        return subAccounts;
    }

    public Account getParent() {
        return parent;
    }

    public void setParent(final Account parent) {
        this.parent = parent;
    }

    public boolean add(final Account subItem) {
        subItem.setParent(this);
        return this.subAccounts.add(subItem);
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
