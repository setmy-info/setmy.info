package info.setmy.services.text.linguistics.model;

/**
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class Quote extends MultiTextItem {

    public Quote() {
        super(TextBlockType.QUOTE);
    }

    public Quote(final TextBlockType type) {
        super(type);
    }
}
