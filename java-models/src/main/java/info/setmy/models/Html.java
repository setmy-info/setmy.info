package info.setmy.models;

import info.setmy.exceptions.UncheckedException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static info.setmy.services.TimeService.timeService;
import static info.setmy.services.UrlService.urlService;
import static java.util.Objects.isNull;
import static java.util.Optional.*;

public class Html {

    private Optional<URL> url = empty();

    public static Optional<Html> newHtml(final String fileOrUrl) {
        final LambdaReturn<Html> lambdaReturn = new LambdaReturn<>();
        urlService.toUrl(fileOrUrl).ifPresentOrElse(
            url -> lambdaReturn.setValue(newHtml(url)),
            () -> lambdaReturn.setValue(newHtml(new File(fileOrUrl)))
        );
        return lambdaReturn.getValue();
    }

    public static Html newHtml(final URL url) {
        return new Html(url);
    }

    public static Html newHtml(final File file) {
        return new Html(file);
    }

    private Html(final URL url) {
        this.url = ofNullable(url);
    }

    private Html(final File file) {
        if (isNull(file)) {
            return;
        }
        try {
            url = of(file.toURI().toURL());
        } catch (MalformedURLException e) {
            throw new UncheckedException(e);
        }
    }

    public Optional<Document> getDocument() {
        final LambdaReturn<Document> lambdaReturn = new LambdaReturn<>();
        this.url.ifPresent(url -> {
            try {
                lambdaReturn.setValue(Jsoup.parse(url, timeService.secondsToMillis(60)));
            } catch (IOException e) {
                throw new UncheckedException(e);
            }
        });
        return lambdaReturn.getValue();
    }
}
