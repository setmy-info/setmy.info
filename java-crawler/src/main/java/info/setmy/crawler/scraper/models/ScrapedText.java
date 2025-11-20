package info.setmy.crawler.scraper.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Getter
@Setter
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

    @JsonProperty("paddingLeft")
    private int paddingLeft;

    @JsonProperty("paddingTop")
    private int paddingTop;

    @JsonProperty("paddingRight")
    private int paddingRight;

    @JsonProperty("paddingBottom")
    private int paddingBottom;

    @JsonProperty("marginLeft")
    private int marginLeft;

    @JsonProperty("marginTop")
    private int marginTop;

    @JsonProperty("marginRight")
    private int marginRight;

    @JsonProperty("marginBottom")
    private int marginBottom;

    @JsonProperty("isVisible")
    private Boolean visible;

    @JsonIgnore
    private Location[] locationArray;

    public boolean haveTextOrUrl() {
        return isNotBlank(text) || isNotBlank(url);
    }

    public void setLocationArray(final String locationString) {
        if (isNotBlank(locationString)) {
            final String[] parts = locationString.split("\\.");
            setLocationArray(parse(parts));
        }
    }

    public void setLocationArray(final Location[] locationArray) {
        this.locationArray = locationArray;
    }

    public String toString() {
        return text;
    }

    public void setLocation(final String location) {
        this.location = location;
        setLocationArray(location);
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
        final String[] split = part.split("\\[");
        if (split.length >= 2) {
            var name = split[0];
            var index = split[1].replace("]", "");
            return new Location(parseInt(index), name);
        }
        return new Location(-1, "");
    }
}
