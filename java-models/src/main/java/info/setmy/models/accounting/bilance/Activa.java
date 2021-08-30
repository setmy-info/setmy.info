package info.setmy.models.accounting.bilance;

/**
 * Activa
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Activa {

    private CurrentAssets currentAssets;

    private NonCurrentAssets nonCurrentAssets;

    public CurrentAssets getCurrentAssets() {
        return currentAssets;
    }

    public void setCurrentAssets(CurrentAssets currentAssets) {
        this.currentAssets = currentAssets;
    }

    public NonCurrentAssets getNonCurrentAssets() {
        return nonCurrentAssets;
    }

    public void setNonCurrentAssets(NonCurrentAssets nonCurrentAssets) {
        this.nonCurrentAssets = nonCurrentAssets;
    }
}
