package info.setmy.crawler.scraper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Text {

    private String title;
    private List<ScrapedText> scrapedText = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setScrapedText(final ScrapedText[] result) {
        scrapedText = Arrays.stream(result).toList();
    }

    public List<ScrapedText> getScrapedText() {
        return scrapedText;
    }
}
