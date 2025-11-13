package info.setmy.crawler.scraper;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

class FirefoxProfileFileTest {

    FirefoxProfileFile firefoxProfileFile;

    @Test
    @DisabledIfEnvironmentVariable(named = "GITHUB_ACTIONS", matches = "true")
    void test() {
        final String profileName = "selenium-test";
        firefoxProfileFile = new FirefoxProfileFile(profileName);
        final File profileFile = firefoxProfileFile.getProfileFile();
        assertThat(profileFile).isNotNull();
        assertThat(profileFile.getName()).endsWith(profileName);
    }
}
