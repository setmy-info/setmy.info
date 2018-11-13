package info.setmy.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Contacts extends Entity {

    private List<String> phones = new ArrayList<>();
    private List<String> mobilePhones = new ArrayList<>();
    private List<String> emails = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public List<String> getMobilePhones() {
        return mobilePhones;
    }

    public void setMobilePhones(List<String> mobilePhones) {
        this.mobilePhones = mobilePhones;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
