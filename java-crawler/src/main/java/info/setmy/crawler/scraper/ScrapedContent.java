package info.setmy.crawler.scraper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScrapedContent {

    private final String url;
    private List<ScrapedText> scrapedText = new ArrayList<>();
    private final MetaData metaData = new MetaData();

    public ScrapedContent(final String url) {
        this.url = url;
    }

    public void setScrapedText(final ScrapedText[] result) {
        scrapedText = Arrays.stream(result)
            .filter(scrapedText1 -> scrapedText1.haveTextOrUrl())
            .toList();
        calcMetaDataStatistics();
    }

    private void calcMetaDataStatistics() {
        scrapedText.forEach(scraped -> {
            metaData.getStatisticsData().addFontSize(scraped.getFontSize());
            metaData.getStatisticsData().addColor(scraped.getColor());
            metaData.getStatisticsData().addBackgroundColor(scraped.getBackgroundColor());
        });
    }

    public List<ScrapedText> getScrapedText() {
        return scrapedText;
    }

    public String getUrl() {
        return url;
    }

    public MetaData getMetaData() {
        return metaData;
    }
}
