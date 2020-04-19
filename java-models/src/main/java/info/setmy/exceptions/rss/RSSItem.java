package info.setmy.exceptions.rss;

import static info.setmy.exceptions.rss.RSS.RSS_NAMESPACE;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@XmlType(name = "", propOrder = {"title", "link", "description", "pubDate", "guid"})
@XmlRootElement(name = "item", namespace = RSS_NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class RSSItem {

    @XmlElement(name = "title", namespace = RSS_NAMESPACE)
    private String title;

    @XmlElement(name = "link", namespace = RSS_NAMESPACE)
    private String link;

    @XmlElement(name = "description", namespace = RSS_NAMESPACE)
    private String description;

    @XmlElement(name = "pubDate", namespace = RSS_NAMESPACE)
    @XmlJavaTypeAdapter(JAXBDateAdapter.class)
    private Date pubDate;

    @XmlElement(name = "guid", namespace = RSS_NAMESPACE)
    private String guid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }
}
