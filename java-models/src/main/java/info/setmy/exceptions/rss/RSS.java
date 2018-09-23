package info.setmy.exceptions.rss;

import static info.setmy.exceptions.rss.RSS.RSS_NAMESPACE;
import javax.xml.bind.annotation.*;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@XmlType(name = "", propOrder = {"channel"})
@XmlRootElement(name = "rss", namespace = RSS_NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class RSS {

    public static final String RSS_NAMESPACE = "http://setmy.info/rss";
    public static final String DEFAULT_RSS_VERSION = "2.0";

    @XmlAttribute(name = "version", namespace = RSS_NAMESPACE)
    private String version = DEFAULT_RSS_VERSION;

    @XmlElement(name = "channel", namespace = RSS_NAMESPACE)
    private RSSChannel channel;

    public RSSChannel getChannel() {
        return channel;
    }

    public void setChannel(RSSChannel channel) {
        this.channel = channel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
