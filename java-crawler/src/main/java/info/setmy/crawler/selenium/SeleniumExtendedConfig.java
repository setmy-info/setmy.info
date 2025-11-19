package info.setmy.crawler.selenium;

import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record SeleniumExtendedConfig(
    SeleniumConfig seleniumConfig,
    List<String> scriptNames
) {
}
