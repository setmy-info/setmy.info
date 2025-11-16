package info.setmy.crawler.scraper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;

import static info.setmy.crawler.scraper.UserAgentService.userAgentService;

@Log4j2
class CheckIT {

    @Test
    @Disabled
    void firefoxOptions() throws MalformedURLException {
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        RemoteWebDriver driver = new RemoteWebDriver(URI.create("http://localhost:4444").toURL(), options);

        driver.close();
    }

    @Test
    @Disabled
    void firefoxOptionsWithProfile() throws MalformedURLException {
        final File profileDir = new FirefoxProfileFile("selenium-test").getProfileFile();
        final FirefoxProfile profile = new FirefoxProfile(profileDir);
        final FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        RemoteWebDriver driver = new RemoteWebDriver(URI.create("http://localhost:4444").toURL(), options);
        options.setCapability("se:headers", new HashMap<String, String>() {{
            put("User-Agent", userAgentService.randomUserAgent());
        }});


        driver.close();
    }

    /*
    From Selenium node  logs:
    04:09:05.825 INFO [NodeOptions.report] - Adding Chrome for {"browserName": "chrome","platformName": "Windows 11"} 12 times
    04:09:05.827 INFO [NodeOptions.report] - Adding Edge for {"browserName": "MicrosoftEdge","platformName": "Windows 11"} 12 times
    04:09:05.828 INFO [NodeOptions.report] - Adding Firefox for {"browserName": "firefox","platformName": "Windows 11"} 12 times
    04:09:05.831 INFO [NodeOptions.report] - Adding Internet Explorer for {"browserName": "internet explorer","platformName": "Windows 11"}
    */
    @Test
    @Disabled
    void test2() throws MalformedURLException {
        final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("browserName", "firefox");
        //desiredCapabilities.setCapability("binary", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        desiredCapabilities.setCapability("platformName", "Windows 11");

        //desiredCapabilities.setBrowserName("firefox");
        /*
        desiredCapabilities.setCapability("se:headers", new HashMap<String, String>() {{
            put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:122.0) Gecko/20100101 Firefox/122.0");
        }});
        */
        //desiredCapabilities.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        //desiredCapabilities.setCapability("se:name", "My Test Name");
        //desiredCapabilities.setCapability("se:build", "Build-123");
        //desiredCapabilities.setCapability("se:tags", List.of("smoke", "regression"));

        RemoteWebDriver driver = new RemoteWebDriver(URI.create("http://localhost:4444").toURL(), desiredCapabilities);

        driver.close();
    }

    @Test
    void loggingTest() {
        log.debug("Debug!");
        log.info("Info!");
    }
}
