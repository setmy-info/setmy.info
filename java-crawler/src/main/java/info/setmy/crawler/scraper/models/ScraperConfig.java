package info.setmy.crawler.scraper.models;

import lombok.Getter;

@Getter
public final class ScraperConfig {

    /*
    private final Optional<String> hostName;

    private final Optional<Integer> port;

    private final boolean headless;

    private final Map<String, List<String>> scripts = new HashMap<>();


    public ScraperConfig(final String hostName, final int port) {
        this.hostName = of(hostName);
        this.port = of(port);
        this.headless = true;
    }

    public ScraperConfig(final String hostName, final int port, boolean headless) {
        this.hostName = of(hostName);
        this.port = of(port);
        this.headless = headless;
    }


    public List<String> findScripts(final String urlString) {
        final List<String> result = new ArrayList<>();
        try {
            final URL url = URI.create(urlString).toURL();
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
        return unmodifiableList(result);
    }

    public void addScript(final String hostEnding, final String scriptUrl) {
        getNullAsNew(hostEnding).get().add(scriptUrl);
    }

    public Optional<List<String>> getNullAsNew(final String hostEnding) {
        final Optional<List<String>> mapEntryOptionalList = get(hostEnding);
        if (mapEntryOptionalList.isPresent()) {
            return mapEntryOptionalList;
        }
        final List<String> list = new ArrayList<>();
        scripts.put(hostEnding, list);
        return of(list);
    }

    public Optional<List<String>> get(final String hostEnding) {
        return ofNullable(scripts.get(hostEnding));
    }

     */
}
