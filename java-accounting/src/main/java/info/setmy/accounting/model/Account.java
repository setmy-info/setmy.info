package info.setmy.accounting.model;

import info.setmy.models.NamedEntity;
import org.ietf.jgss.Oid;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Account extends NamedEntity {

    private Oid oid;

    public Oid getOid() {
        return oid;
    }

    public void setOid(Oid oid) {
        this.oid = oid;
    }
}
