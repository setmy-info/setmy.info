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

    private final Map<String, List<String>> scripts = new HashMap<>();

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
            for (Map.Entry<String, List<String>> entry : scripts.entrySet()) {
                final String hostEnding = entry.getKey();
                final List<String> scriptUrls = entry.getValue();
                if (host.endsWith(hostEnding)) {
                    result.addAll(scriptUrls);
                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void addScript(final String hostEnding, final String scriptUrl) {
        List<String> list = scripts.get(hostEnding);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(scriptUrl);
        scripts.put(hostEnding, list);
    }
}
