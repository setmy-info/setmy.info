package info.setmy.services.text.linguistics.model;

/**
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class TextItem {

    private TextItem parent;

    private final TextBlockType blockType;

    private int from;

    private int to;

    private final StringBuilder stringBuilder = new StringBuilder();

    private String text;

    private Object metaData;

    public TextItem(final TextBlockType blockType) {
        this.blockType = blockType;
    }

    public void add(final char character) {
        stringBuilder.append(character);
    }

    public void finish() {
        this.setText(stringBuilder.toString());
        stringBuilder.setLength(0);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextItem getParent() {
        return parent;
    }

    public void setParent(TextItem parent) {
        this.parent = parent;
    }

    public Object getMetaData() {
        return metaData;
    }

    public void setMetaData(Object metaData) {
        this.metaData = metaData;
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public int length() {
        if (text != null) {
            return text.length();
        }
        return this.stringBuilder.length();
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public TextBlockType getBlockType() {
        return blockType;
    }
}
