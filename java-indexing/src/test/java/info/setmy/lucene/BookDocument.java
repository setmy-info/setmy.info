package info.setmy.lucene;

import info.setmy.lucene.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

/**
 * Example domain specific model.
 */
public class BookDocument extends Document {

    public BookDocument() {
        super();
    }

    public BookDocument(org.apache.lucene.document.Document document) {
        super(document);
    }

    public String getISBN() {
        return getLuceneDocument().get("isbn");
    }

    public String getTitle() {
        return getLuceneDocument().get("title");
    }

    public void setISBN(final String isbn) {
        add(new TextField("isbn", isbn, Field.Store.YES));
    }

    public void setTitle(final String title) {
        add(new TextField("title", title, Field.Store.YES));
    }
}
