package info.setmy.crawler.scraper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScrapedContent {

    private String url;
    private List<ScrapedText> scrapedText = new ArrayList<>();

    public void setScrapedText(final ScrapedText[] result) {
        scrapedText = Arrays.stream(result).toList();
    }

    public List<ScrapedText> getScrapedText() {
        return scrapedText;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
