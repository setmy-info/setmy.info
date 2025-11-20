package info.setmy.crawler.scraper;

import info.setmy.crawler.scraper.models.FirefoxProfileFile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

class FirefoxProfileFileTest {

    FirefoxProfileFile firefoxProfileFile;

    /**
     * firefox --createprofile "selenium-test"
     * export PATH=/opt/firefox:${PATH}
     * set PATH="C:\Program Files\Mozilla Firefox";%PATH%
     * firefox -P
     *
     */
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
