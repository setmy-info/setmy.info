package info.setmy.crawler.dal;

public class DataSourceFactory {

    private static final DataSourceFactory INSTANCE = new DataSourceFactory();

    public static DataSourceFactory getInstance() {
        return INSTANCE;
    }
}
