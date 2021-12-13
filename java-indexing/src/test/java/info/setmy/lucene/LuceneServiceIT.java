package info.setmy.lucene;

import info.setmy.models.Paging;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class LuceneServiceIT {

    static final Logger LOG = LoggerFactory.getLogger(LuceneServiceIT.class);

    private LuceneService diskLuceneService;

    private final static String DIR = "target/lucene";

    private final static String INDEX = "index";

    private final BookDocument[] books = new BookDocument[4];

    @BeforeEach
    public void before() throws IOException {
        FileUtils.deleteDirectory(new File(DIR));
        diskLuceneService = new LuceneService(DIR, INDEX);
        diskLuceneService.init();
        prepareBooks();
    }

    @AfterEach
    public void after() {
        diskLuceneService.close();
    }

    @Test
    public void diskIndexing() throws IOException {
        diskLuceneService.write(books[0]);
        diskLuceneService.write(books[1]);
        diskLuceneService.write(books[2]);
        diskLuceneService.write(books[3]);

        final List<BookDocument> documentList = diskLuceneService.query("lucene", "title", new Paging(), BookDocument.class);

        LOG.info("Book: '{}' ISBN: {}", documentList.get(0).getTitle(), documentList.get(0).getISBN());
        assertThat(documentList.get(0).getISBN()).isEqualTo("193398817");
        assertThat(documentList.get(0).getTitle()).isEqualTo("Lucene in Action");
        LOG.info("Book: '{}' ISBN: {}", documentList.get(1).getTitle(), documentList.get(0).getISBN());
        assertThat(documentList.get(1).getISBN()).isEqualTo("55320055Z");
        assertThat(documentList.get(1).getTitle()).isEqualTo("Lucene for Dummies");
    }

    public void prepareBooks() {
        BookDocument doc = new BookDocument();
        doc.setISBN("193398817");
        doc.setTitle("Lucene in Action");
        books[0] = doc;

        doc = new BookDocument();
        doc.setISBN("55320055Z");
        doc.setTitle("Lucene for Dummies");
        books[1] = doc;

        doc = new BookDocument();
        doc.setISBN("55063554A");
        doc.setTitle("Managing Gigabytes");
        books[2] = doc;

        doc = new BookDocument();
        doc.setISBN("9900333X");
        doc.setTitle("The Art of Computer Science");
        books[3] = doc;
    }
}
