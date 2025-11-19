package info.setmy.crawler.selenium;

import info.setmy.crawler.browser.models.Browser;
import lombok.Builder;

import java.util.Optional;

@Builder(toBuilder = true)
public record SeleniumConfig(
    Optional<String> hostName,
    Optional<Integer> port,
    boolean headless,
    Optional<Browser> browser
) {
}
