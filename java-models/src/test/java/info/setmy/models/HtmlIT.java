package info.setmy.models;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static info.setmy.models.Html.newHtml;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.platform.commons.util.StringUtils.isNotBlank;

class HtmlIT {

    private final static String URL = "https://www.riigiteataja.ee/lyhendid.html";

    @Test
    @Disabled
    public void readEstonianLaws() {
        final Optional<Html> optionalHtml = newHtml(URL);
        assertThat(optionalHtml).isPresent();

        final Optional<Document> optionalDocument = optionalHtml.get().getDocument();
        assertThat(optionalDocument).isPresent();

        optionalDocument.ifPresent(document -> {
            System.out.println("Location URL: " + document.location());
            document.body().forEach(element -> {
                final String href = element.attr("href");
                if (isNotBlank(href) && href.startsWith("https://www.riigiteataja.ee/akt/")) {
                    System.out.println("A href URL: " + href);
                }
            });
        });
    }
}
