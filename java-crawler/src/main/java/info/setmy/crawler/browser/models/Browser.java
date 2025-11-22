package info.setmy.crawler.browser.models;

import lombok.Builder;
import org.openqa.selenium.Dimension;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static info.setmy.crawler.browser.models.BrowserType.randomBrowserType;

// TODO : Browser with other parameters to send to backend as headers
@Builder(toBuilder = true)
public record Browser(
    String userAgent,
    Dimension windowSize,
    BrowserType browserType,
    BrowserPreferences browserPreferences
) {

    //<editor-fold defaultstate="constants" desc="Instance code">
    public static String[] FIREFOX_USER_AGENTS = {
        //FF
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:145.0) Gecko/20100101 Firefox/145.0",//Exists
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:122.0) Gecko/20100101 Firefox/122.0",
        "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:78.0) Gecko/20100101 Firefox/78.0",
        "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:100.0) Gecko/20100101 Firefox/100.0",
        "Mozilla/5.0 (X11; Linux x86_64; rv:60.0) Gecko/20100101 Firefox/60.0",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:130.0) Gecko/20100101 Firefox/130.0",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 14_1; rv:130.0) Gecko/20100101 Firefox/130.0",
        "Mozilla/5.0 (X11; Linux x86_64; rv:130.0) Gecko/20100101 Firefox/130.0",
        "Mozilla/5.0 (Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko",
        // Most common
        //https://www.useragents.me/#most-common-desktop-useragents-json-csv
        //https://explore.whatismybrowser.com/useragents/explore/
        //https://useragents.io/
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:136.0) Gecko/20100101 Firefox/136.0",
    };
    public static String[] CHROME_USER_AGENTS = {
        //Chrome
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Safari/537.36",//Exists
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36",
        "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36",
        "Mozilla/5.0 (X11; Fedora; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36",
        "Mozilla/5.0 (X11; Debian; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chromium/81.0.4044.138 Chrome/81.0.4044.138 Safari/537.36",
        //Most common
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.3",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.3",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.3",
    };
    public static String[] EDGE_USER_AGENTS = {
        //Edge
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Safari/537.36 Edg/142.0.0.0",//Exists
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Edge/18.18363",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36 Edg/124.0.0.0",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 14_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36 Edg/124.0.0.0",
        "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36 Edg/123.0.0.0",
        //Most common
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36 Edg/134.0.0.0",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36 Edg/132.0.0.0",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36 Edge/18.1958",
    };
    public static String[] OPERA_USER_AGENTS = {
        // Looks like Opera
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) OPR/107.0.0.0 Chrome/124.0.0.0 Safari/537.36",
        "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) OPR/105.0.0.0 Chrome/122.0.0.0 Safari/537.36",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 13_5) AppleWebKit/537.36 (KHTML, like Gecko) OPR/107.0.0.0 Chrome/124.0.0.0 Safari/537.36",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36 OPR/108.0.0.0",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 14_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36 OPR/108.0.0.0",
        "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36 OPR/107.0.0.0",
        // Most common
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36 OPR/117.0.0.0",

    };
    public static String[] SAFARI_USER_AGENTS = {
        //Looks like Safari
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 14_1) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.3 Safari/605.1.15",
        "Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0 Safari/605.1.15",
        "Mozilla/5.0 (iPhone; CPU iPhone OS 17_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.3 Mobile/15E148 Safari/604.1",
        "Mozilla/5.0 (iPad; CPU OS 17_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.2 Mobile/15E148 Safari/604.1",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36",
        "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36",
        //Most common
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.10 Safari/605.1.1"
    };
    public static String[] UNKNOWN_USER_AGENTS = {
        "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.3",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36 Trailer/93.3.8652.5",

    };
    //</editor-fold>

    public static Browser newBrowser() {
        final var browserType = randomBrowserType();
        return Browser.builder()
            .userAgent(randomUserAgentByBrowserType(browserType))
            .windowSize(randomWindowSize())
            .browserType(browserType)
            .browserPreferences(createBrowserPreferences(browserType))
            .build();
    }

    private static String randomUserAgentByBrowserType(final BrowserType browserType) {
        final String[] userAgents = switch (browserType) {
            case FIREFOX -> FIREFOX_USER_AGENTS;
            case CHROME -> CHROME_USER_AGENTS;
            case EDGE -> EDGE_USER_AGENTS;
            case SAFARI -> SAFARI_USER_AGENTS;
            case OPERA -> OPERA_USER_AGENTS;
            case UNKNOWN -> UNKNOWN_USER_AGENTS;
        };
        return randomUserAgent(userAgents);
    }

    public static String randomUserAgent(final String[] userAgents) {
        final int randomIndex = ThreadLocalRandom.current().nextInt(userAgents.length);
        return userAgents[randomIndex];
    }

    public static Dimension randomWindowSize() {
        final Random random = new Random();
        final int width = 1000 + random.nextInt(600);  // 1000–1600
        final int height = 700 + random.nextInt(400);   // 700–1100
        return new Dimension(width, height);
    }

    private static BrowserPreferences createBrowserPreferences(final BrowserType browserType) {
        return switch (browserType) {
            case FIREFOX -> BrowserPreferences.builder()
                .acceptHeader("image/avif,image/webp,image/png,image/svg+xml,image/*;q=0.8,*/*;q=0.5")
                .acceptLanguage("en-US,en;q=0.5")
                .acceptEncoding("gzip, deflate, br, zstd")
                .trackingProtection(true)
                .resistFingerprinting(false)
                .build();
            case CHROME, OPERA -> BrowserPreferences.builder()
                .acceptHeader("image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8")
                .acceptLanguage("en-US,en;q=0.9")
                .acceptEncoding("gzip, deflate, br, zstd")
                .trackingProtection(false)
                .resistFingerprinting(false)
                .build();
            case EDGE -> BrowserPreferences.builder()
                .acceptHeader("image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8")
                .acceptLanguage("en-US,en;q=0.9,et;q=0.8")
                .acceptEncoding("gzip, deflate, br, zstd")
                .trackingProtection(false)
                .resistFingerprinting(false)
                .build();
            case SAFARI -> BrowserPreferences.builder()
                .acceptHeader("image/avif,image/webp,image/png,image/svg+xml,image/*;q=0.8,*/*;q=0.5")
                .acceptLanguage("en-US,en;q=0.9")
                .acceptEncoding("gzip, deflate, br")
                .trackingProtection(true)
                .resistFingerprinting(false)
                .build();
            default -> BrowserPreferences.defaults();
        };
    }

    public boolean isNot(final BrowserType browserType) {
        return this.browserType != browserType;
    }
}
