package info.setmy.models;

import info.setmy.models.accounting.BICCode;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class Bank extends Company {

    private Contacts contacts;

    private BICCode bicCode;

    public BICCode getBicCode() {
        return bicCode;
    }

    public void setBicCode(BICCode bicCode) {
        this.bicCode = bicCode;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }
}
