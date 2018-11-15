package info.setmy.accounting.model;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BankAccount {

    private BICCode bicCode;

    private IBAN iban;

    public BankAccount() {
    }

    public BankAccount(final String bicCodeString, final String ibanString) {
        parse(bicCodeString, ibanString);
    }

    public final void parse(final String bicCodeString, final String ibanString) {
        parseBICCode(bicCodeString);
        parseIBAN(ibanString);
    }

    private void parseBICCode(final String bicCodeString) {
        bicCode = new BICCode(bicCodeString);
    }

    private void parseIBAN(final String ibanString) {
        iban = new IBAN(ibanString);
    }

    public BICCode getBicCode() {
        return bicCode;
    }

    public void setBicCode(BICCode bicCode) {
        this.bicCode = bicCode;
    }

    public IBAN getIban() {
        return iban;
    }

    public void setIban(IBAN iban) {
        this.iban = iban;
    }
}
