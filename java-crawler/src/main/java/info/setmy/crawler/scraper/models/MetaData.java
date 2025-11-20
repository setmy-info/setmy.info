package info.setmy.crawler.scraper.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MetaData {

    private final StatisticsData statisticsData = new StatisticsData();
    private int width;
    private int height;
}
