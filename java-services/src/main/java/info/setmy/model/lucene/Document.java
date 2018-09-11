package info.setmy.model.lucene;

import org.apache.lucene.document.Field;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Document {

    private final org.apache.lucene.document.Document document;

    public Document() {
        this.document = new org.apache.lucene.document.Document();
    }

    public Document(org.apache.lucene.document.Document document) {
        this.document = document;
    }

    public void add(final Field field) {
        document.add(field);
    }

    public org.apache.lucene.document.Document getLuceneDocument() {
        return document;
    }
}
