package info.setmy.crawler.scraper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import static java.lang.Long.parseLong;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

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

    @JsonProperty("location")
    private String location;

    @JsonIgnore
    private Location[] locationArray;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getText() {
        return text;
    }

    public boolean haveTextOrUrl() {
        return isNotBlank(text) || isNotBlank(url);
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

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
        setLocationArray(location);
    }

    public Location[] getLocationArray() {
        return locationArray;
    }

    public void setLocationArray(final String locationString) {
        if (isNotBlank(locationString)) {
            final String[] parts = locationString.split(";");
            setLocationArray(parse(parts));
        }
    }

    private Location[] parse(final String[] parts) {
        if (parts.length > 0) {
            final Location[] result = new Location[parts.length];
            for (int i = 0; i < parts.length; i++) {
                result[i] = parse(parts[i]);
            }
            return result;
        }
        return new Location[0];
    }

    private Location parse(final String part) {
        final String[] split = part.split(":");
        if (split.length >= 2) {
            return new Location(parseLong(split[0]), split[1]);
        }
        return new Location(-1, "");
    }

    public void setLocationArray(final Location[] locationArray) {
        this.locationArray = locationArray;
    }
}
