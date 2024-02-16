package info.setmy.crawler.scraper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ScrapedContent {

    private final String url;
    private List<ScrapedText> scrapedTexts = new ArrayList<>();
    private final MetaData metaData = new MetaData();

    public ScrapedContent(final String url) {
        this.url = url;
    }

    public void setScrapedTexts(final ScrapedText[] result) {
        final List<ScrapedText> list = Arrays.stream(result)
            .filter(scrapedText1 -> scrapedText1.haveTextOrUrl())
            .toList();
        scrapedTexts = list.stream()
            .limit(list.size() - 10) // 10 control buttons with texts
            .collect(Collectors.toUnmodifiableList());
        calcMetaDataStatistics();
    }

    private void calcMetaDataStatistics() {
        scrapedTexts.forEach(scraped -> {
            metaData.getStatisticsData().addFontSize(scraped.getFontSize());
            metaData.getStatisticsData().addColor(scraped.getColor());
            metaData.getStatisticsData().addBackgroundColor(scraped.getBackgroundColor());
        });
    }

    public List<ScrapedText> getScrapedTexts() {
        return scrapedTexts;
    }

    public String getUrl() {
        return url;
    }

    public MetaData getMetaData() {
        return metaData;
    }
}
