package info.setmy.services.text.linguistics.model;

/**
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class Clause extends MultiTextItem {

    public Clause() {
        super(TextBlockType.CLAUSE);
    }

    public Clause(final TextBlockType type) {
        super(type);
    }
}
