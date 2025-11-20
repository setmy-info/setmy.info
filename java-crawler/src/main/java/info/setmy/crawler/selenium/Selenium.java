package info.setmy.crawler.selenium;

import info.setmy.crawler.browser.models.BrowserPreferences;
import info.setmy.crawler.scraper.models.FirefoxProfileFile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Optional;

import static info.setmy.crawler.browser.models.Browser.newBrowser;
import static info.setmy.crawler.browser.models.BrowserType.FIREFOX;
import static java.time.Duration.ofSeconds;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.openqa.selenium.By.id;

@Log4j2
@Getter
@RequiredArgsConstructor
public final class Selenium {

    public static final String DEFAULT_HUB_HOST_NAME = "localhost";
    public static final int DEFAULT_HUB_PORT = 4444;

    private final SeleniumConfig seleniumConfig;
    private WebDriver webDriver;

    public void init() {
        webDriver = new RemoteWebDriver(
            getUrl(),
            newDesiredCapabilities()
        );
    }

    public void initSize() {
        webDriver.manage().window().setSize(
            seleniumConfig.browser()
                .orElse(newBrowser())
                .windowSize()
        );
    }

    public void initTimeouts() {
        final Duration duration = ofSeconds(10);
        webDriver.manage().timeouts().implicitlyWait(duration);
    }

    private URL getUrl() {
        try {
            final String urlString = getUrlString();
            return new URL(urlString);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getUrlString() {
        return "http://"
            + seleniumConfig.hostName().orElse(DEFAULT_HUB_HOST_NAME)
            + ":" + seleniumConfig.port().orElse(DEFAULT_HUB_PORT) + "/wd/hub";
    }

    private Capabilities newDesiredCapabilities() {
        final File profileDir = new FirefoxProfileFile("selenium-test").getProfileFile();
        final FirefoxProfile profile = new FirefoxProfile(profileDir);
        /*
        final Proxy proxy = new Proxy()
            .setHttpProxy("localhost:8888")
            .setSslProxy("localhost:8888");
        */
        var browser = seleniumConfig.browser().orElse(newBrowser());
        var agent = browser.userAgent();
        profile.setPreference("general.useragent.override", agent);

        if (browser.isNot(FIREFOX)) {
            log.info("Not firefox. Rewriting some headers.");
            final BrowserPreferences browserPreferences = browser.browserPreferences();
            profile.setPreference("network.http.accept.default", browserPreferences.acceptHeader());
            profile.setPreference("intl.accept_languages", browserPreferences.acceptLanguage());
            profile.setPreference("network.http.accept-encoding", browserPreferences.acceptEncoding());
            profile.setPreference("privacy.trackingprotection.enabled", browserPreferences.trackingProtection());
            profile.setPreference("privacy.resistFingerprinting", browserPreferences.resistFingerprinting());
        }

        final FirefoxOptions desiredCapabilities = new FirefoxOptions();
        if (seleniumConfig.headless()) {
            desiredCapabilities.addArguments("-headless");
        }
        desiredCapabilities.setProfile(profile);
        //desiredCapabilities.setProxy(proxy);
        //desiredCapabilities.setAcceptInsecureCerts(true);

        return desiredCapabilities;
    }

    public void quit() {
        webDriver.quit();
    }

    public void close() {
        webDriver.close();
    }

    public void get(final String url) {
        webDriver.get(url);
    }

    public Optional<String> getElementValueById(final String id) {
        return getElementAttributeValueById(id, "value");
    }

    public Optional<String> getElementAttributeValueById(final String id, final String attributeName) {
        return findOptionalElementById(id)
            .map(webElementl -> webElementl.getAttribute(attributeName));
    }

    public Optional<WebElement> findOptionalElementById(final String id) {
        try {
            return of(findElementById(id));
        } catch (NoSuchElementException e) {
            return empty();
        }
    }

    public WebElement findElementById(final String id) {
        return webDriver.findElement(id(id));
    }
}
