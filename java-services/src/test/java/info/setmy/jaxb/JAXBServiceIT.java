package info.setmy.jaxb;

import info.setmy.exceptions.rss.RSS;
import info.setmy.exceptions.rss.RSSChannel;
import info.setmy.exceptions.rss.RSSItem;
import java.util.Date;
import java.util.UUID;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class JAXBServiceIT {

    JAXBService service;

    @Before
    public void setUp() {
        service = new JAXBService();
    }

    @Test
    public void xmlRendering() {
        final RSS rss = new RSS();
        final RSSChannel channel = new RSSChannel();
        final RSSItem item = new RSSItem();
        channel.addItem(item);
        rss.setChannel(channel);

        channel.setTitle("Imre Tabur");
        channel.setLink("http://some.link.example.com");
        channel.setDescription("Description");
        channel.setLanguage("ee-et");
        channel.setWebMaster("Webmaster");
        channel.setManagingEditor("Managing Editor");
        channel.setPubDate(new Date());
        channel.setLastBuildDate(new Date());
        channel.setGenerator("setmy.info");
        channel.setDocs("Docs");

        item.setTitle("Title");
        item.setLink("http://some.link.example.com");
        item.setDescription("Description");
        item.setPubDate(new Date());
        item.setGuid(UUID.randomUUID().toString());

        service.render("target/rss.xml", "./src/test/resources/rss.xsd", null, null, rss);
    }

    @Test
    public void xmlParsing() {
        final RSS rss = service.parse("./src/test/resources/rss.xml", "./src/test/resources/rss.xsd", RSS.class);
        assertThat(rss.getChannel().getTitle(), is(equalTo("Imre Tabur")));
    }
}
