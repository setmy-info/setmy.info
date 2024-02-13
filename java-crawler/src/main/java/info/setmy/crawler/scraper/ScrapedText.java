package info.setmy.crawler.scraper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScrapedText {

    @JsonProperty("nodeName")
    private String nodeName;

    @JsonProperty("text")
    private String text;

    @JsonProperty("url")
    private String url;

    @JsonProperty("y")
    private int y;

    @JsonProperty("x")
    private int x;

    @JsonProperty("width")
    private int width;

    @JsonProperty("height")
    private int height;

    @JsonProperty("color")
    private String color;

    @JsonProperty("fontSize")
    private String fontSize;

    @JsonProperty("fontStyle")
    private String fontStyle;

    @JsonProperty("bold")
    private boolean bold;

    @JsonProperty("italic")
    private boolean italic;

    @JsonProperty("backgroundColor")
    private String backgroundColor;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String toString() {
        return text;
    }
}
