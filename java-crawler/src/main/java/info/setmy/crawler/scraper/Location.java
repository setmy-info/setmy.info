package info.setmy.crawler.scraper;

public class Location {

    private final int index;

    private final String name;

    public Location(final int index, final String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}
