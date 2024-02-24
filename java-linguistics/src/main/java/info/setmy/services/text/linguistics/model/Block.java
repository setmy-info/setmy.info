package info.setmy.services.text.linguistics.model;

/**
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class Block extends MultiTextItem {

    public Block() {
        super(TextBlockType.BLOCK);
    }

    public Block(final TextBlockType type) {
        super(type);
    }
}
