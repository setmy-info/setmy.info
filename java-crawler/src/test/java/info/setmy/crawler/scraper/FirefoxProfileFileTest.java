package info.setmy.crawler.scraper;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

class FirefoxProfileFileTest {

    FirefoxProfileFile firefoxProfileFile;

    @Test
    void test() {
        final String profileName = "selenium-test";
        firefoxProfileFile = new FirefoxProfileFile(profileName);
        final File profileFile = firefoxProfileFile.getProfileFile();
        assertThat(profileFile).isNotNull();
        assertThat(profileFile.getName()).endsWith(profileName);
    }
}
