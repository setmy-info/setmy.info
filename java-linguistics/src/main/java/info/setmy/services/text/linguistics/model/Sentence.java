package info.setmy.services.text.linguistics.model;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Sentence extends MultiTextItem {

    public Sentence() {
        super(TextBlockType.SENTENCE);
    }

    public Sentence(final TextBlockType type) {
        super(type);
    }
}
