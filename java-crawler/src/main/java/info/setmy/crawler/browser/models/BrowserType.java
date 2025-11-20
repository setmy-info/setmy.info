package info.setmy.crawler.browser.models;

import java.util.Random;

public enum BrowserType {

    FIREFOX,
    CHROME,
    EDGE,
    OPERA,
    SAFARI,
    UNKNOWN;

    private final static Random random = new Random();

    public static BrowserType randomBrowserType() {
        final BrowserType[] values = values();
        return values[random.nextInt(values.length)];
    }
}
