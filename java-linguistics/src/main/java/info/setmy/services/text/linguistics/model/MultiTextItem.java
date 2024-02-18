package info.setmy.services.text.linguistics.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class MultiTextItem extends TextItem {

    private final List<TextItem> subItems = new ArrayList<>();

    public MultiTextItem(final TextBlockType blockType) {
        super(blockType);
    }

    public boolean add(final TextItem textItem) {
        textItem.setParent(this);
        return getSubItems().add(textItem);
    }

    public List<TextItem> getSubItems() {
        return subItems;
    }
}
