package info.setmy.models.accounting;

import info.setmy.models.Entity;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 * @param <S> seller type.
 * @param <R> recipient type.
 */
public class Invoice<S, R> extends Entity {

    private String invoiceNumber;

    private S seller;

    private R recipient;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public S getSeller() {
        return seller;
    }

    public void setSeller(final S seller) {
        this.seller = seller;
    }

    public R getRecipient() {
        return recipient;
    }

    public void setRecipient(final R recipient) {
        this.recipient = recipient;
    }
}
