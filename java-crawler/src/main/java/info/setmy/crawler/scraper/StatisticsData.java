package info.setmy.crawler.scraper;

import java.util.HashSet;
import java.util.Set;

public class StatisticsData {

    private final Set<String> fontSizes = new HashSet<>();
    private final Set<String> colors = new HashSet<>();
    private final Set<String> backgroundColors = new HashSet<>();

    public boolean addFontSize(final String fontSize) {
        return fontSizes.add(fontSize);
    }

    public boolean addColor(final String color) {
        return colors.add(color);
    }

    public boolean addBackgroundColor(final String backgroundColor) {
        return backgroundColors.add(backgroundColor);
    }

    public Set<String> getFontSizes() {
        return fontSizes;
    }

    public Set<String> getColors() {
        return colors;
    }

    public Set<String> getBackgroundColors() {
        return backgroundColors;
    }
}
