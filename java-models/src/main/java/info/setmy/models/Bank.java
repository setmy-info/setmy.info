package info.setmy.models;

import info.setmy.models.accounting.BICCode;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Bank extends Company {

    private BICCode bicCode;

    public BICCode getBicCode() {
        return bicCode;
    }

    public void setBicCode(BICCode bicCode) {
        this.bicCode = bicCode;
    }
}
