package info.setmy.crawler.scraper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static info.setmy.crawler.scraper.Constants.DEFAULT_HUB_HOST_NAME;
import static info.setmy.crawler.scraper.Constants.DEFAULT_HUB_PORT;
import static java.util.Optional.of;

public class ScraperConfig {

    private final Optional<String> hostName;

    private final Optional<Integer> port;

    private final Map<String, String> scripts = new HashMap<>();

    public ScraperConfig(final String hostName, final int port) {
        this.hostName = of(hostName);
        this.port = of(port);
    }

    public URL getUrl() {
        try {
            final String urlString = getUrlString();
            return new URL(urlString);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUrlString() {
        return "http://" + hostName.orElse(DEFAULT_HUB_HOST_NAME) + ":" + port.orElse(DEFAULT_HUB_PORT) + "/wd/hub";
    }

    public List<String> findScripts(final String urlString) {
        final List<String> result = new ArrayList<>();
        try {
            final URL url = new URL(urlString);
            final String host = url.getHost();
            for (Map.Entry<String, String> entry : scripts.entrySet()) {
                final String hostEnding = entry.getKey();
                final String scriptUrl = entry.getValue();
                if (host.endsWith(hostEnding)) {
                    result.add(scriptUrl);
                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void addScript(final String hostEnding, final String scriptUrl) {
        scripts.put(hostEnding, scriptUrl);
    }
}
