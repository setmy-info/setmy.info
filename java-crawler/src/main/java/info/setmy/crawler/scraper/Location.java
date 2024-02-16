package info.setmy.crawler.scraper;

public class Location {

    private final long index;

    private final String name;

    public Location(long index, String name) {
        this.index = index;
        this.name = name;
    }

    public long getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}
