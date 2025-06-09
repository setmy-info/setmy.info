package info.setmy.crawler.scraper;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

class CheckIT {

    @Test
    void test() throws MalformedURLException {
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);

        driver.close();
    }

    @Test
    @Disabled
    void test2() throws MalformedURLException {
        final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("browserName", "firefox");
        desiredCapabilities.setCapability("binary", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");

        //desiredCapabilities.setBrowserName("firefox");
        /*
        desiredCapabilities.setCapability("se:headers", new HashMap<String, String>() {{
            put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:122.0) Gecko/20100101 Firefox/122.0");
        }});
        */
        //desiredCapabilities.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        desiredCapabilities.setCapability("se:name", "My Test Name");
        desiredCapabilities.setCapability("se:build", "Build-123");
        desiredCapabilities.setCapability("se:tags", List.of("smoke", "regression"));

        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), desiredCapabilities);

        driver.close();
    }
}
