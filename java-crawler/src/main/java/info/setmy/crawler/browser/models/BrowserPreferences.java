package info.setmy.crawler.browser.models;

import lombok.Builder;

@Builder(toBuilder = true)
public record BrowserPreferences(
    String acceptHeader,
    String acceptLanguage,
    String acceptEncoding,
    boolean trackingProtection,
    boolean resistFingerprinting
) {
    public static BrowserPreferences defaults() {
        return BrowserPreferences.builder()
            .acceptHeader("*/*")
            .acceptLanguage("en-US,en;q=0.5")
            .acceptEncoding("gzip, deflate, br")
            .trackingProtection(true)
            .resistFingerprinting(false)
            .build();
    }
}
