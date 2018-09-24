package info.setmy.exceptions.rss;

import static info.setmy.exceptions.rss.RSS.RSS_NAMESPACE;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@XmlType(name = "", propOrder = {
    "title",
    "link",
    "description",
    "language",
    "pubDate",
    "lastBuildDate",
    "docs",
    "generator",
    "managingEditor",
    "webMaster",
    "items"
})
@XmlRootElement(name = "channel", namespace = RSS_NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class RSSChannel {

    @XmlElement(name = "language", namespace = RSS_NAMESPACE)
    private String language;

    @XmlElement(name = "docs", namespace = RSS_NAMESPACE)
    private String docs;

    @XmlElement(name = "managingEditor", namespace = RSS_NAMESPACE)
    private String managingEditor;

    @XmlElement(name = "webMaster", namespace = RSS_NAMESPACE)
    private String webMaster;

    @XmlElement(name = "generator", namespace = RSS_NAMESPACE)
    private String generator = "Hear And See Systems LLC Web App";

    @XmlElement(name = "title", namespace = RSS_NAMESPACE)
    private String title;

    @XmlElement(name = "link", namespace = RSS_NAMESPACE)
    private String link;

    @XmlElement(name = "description", namespace = RSS_NAMESPACE)
    private String description;

    @XmlElement(name = "item", namespace = RSS_NAMESPACE)
    private final List<RSSItem> items = new ArrayList<RSSItem>();

    @XmlElement(name = "pubDate", namespace = RSS_NAMESPACE)
    @XmlJavaTypeAdapter(JAXBDateAdapter.class)
    private Date pubDate;

    @XmlElement(name = "lastBuildDate", namespace = RSS_NAMESPACE)
    @XmlJavaTypeAdapter(JAXBDateAdapter.class)
    private Date lastBuildDate;

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

    public boolean addItem(RSSItem item) {
        return this.items.add(item);
    }

    public void clearItems() {
        this.items.clear();
    }

    public List<RSSItem> getItems() {
        return this.items;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public String getWebMaster() {
        return webMaster;
    }

    public void setWebMaster(String webMaster) {
        this.webMaster = webMaster;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDocs() {
        return docs;
    }

    public void setDocs(String docs) {
        this.docs = docs;
    }

    public String getManagingEditor() {
        return managingEditor;
    }

    public void setManagingEditor(String managingEditor) {
        this.managingEditor = managingEditor;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Date getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(Date lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }
}
