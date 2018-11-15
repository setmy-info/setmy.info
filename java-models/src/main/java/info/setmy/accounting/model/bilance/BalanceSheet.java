package info.setmy.accounting.model.bilance;

import java.time.LocalDateTime;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BalanceSheet {

    private LocalDateTime moment;

    private Activa activa;

    private Passiva passiva;

    public Activa getActiva() {
        return activa;
    }

    public void setActiva(Activa activa) {
        this.activa = activa;
    }

    public Passiva getPassiva() {
        return passiva;
    }

    public void setPassiva(Passiva passiva) {
        this.passiva = passiva;
    }

    public LocalDateTime getMoment() {
        return moment;
    }

    public void setMoment(LocalDateTime moment) {
        this.moment = moment;
    }
}
