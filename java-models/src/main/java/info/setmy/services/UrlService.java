package info.setmy.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class UrlService {

    public static final UrlService urlService = new UrlService();

    public Optional<URL> toUrl(final String fileOrUrl) {
        try {
            return of(new URL(fileOrUrl));
        } catch (MalformedURLException e) {
            return empty();
        }
    }
}
