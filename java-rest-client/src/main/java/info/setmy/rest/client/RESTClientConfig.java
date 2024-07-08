package info.setmy.rest.client;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.BooleanUtils.TRUE;

@Getter
@Builder(toBuilder = true)
public class RESTClientConfig {

    private final Map<String, Object> defaultHeaders = new HashMap<>();

    private final Map<String, String> defaultCookies = new HashMap<>();

    private final Map<String, String> defaultProperties = new HashMap<>();

    private final boolean debugLogging;

    public RESTClientConfig applyDefaultConfig() {
        defaultHeaders.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:127.0) Gecko/20100101 Firefox/127.0");
        //defaultHeaders.put("Accept", "application/json");
        defaultHeaders.put("Accept-Encoding", "gzip, deflate, br, zstd");
        defaultHeaders.put("Accept-Language", "en-US,en;q=0.5");
        defaultHeaders.put("Host", "localhost:8080");
        defaultHeaders.put("Origin", "http://localhost:8080");
        defaultHeaders.put("Referer", "http://localhost:8080/swagger-ui/index.html");
        defaultHeaders.put("Priority", "u=1");
        defaultHeaders.put("Sec-Fetch-Dest", "empty");
        defaultHeaders.put("Sec-Fetch-Mode", "cors");
        defaultHeaders.put("Sec-Fetch-Site", "same-origin");

        defaultCookies.put("locale", "en-US");

        defaultProperties.put("keep-alive", TRUE);
        return this;
    }
}
