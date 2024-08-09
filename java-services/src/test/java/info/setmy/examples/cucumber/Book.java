package info.setmy.examples.cucumber;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class Book {

    private final String title;

    public Book(String title) {
        this.title = title;
    }

    public static Book fromTitle(String title) {
        return new Book(title);
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public String toString() {
        return "Book{" + "title=" + title + '}';
    }
}
