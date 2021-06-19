package info.setmy.lucene;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.util.Version;
import static org.apache.lucene.util.Version.LUCENE_8_8_2;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Read: http://www.lucenetutorial.com/lucene-in-5-minutes.html Query:
 * http://lucene.apache.org/core/4_1_0/queryparser/org/apache/lucene/queryparser/classic/package-summary.html#package_description
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class LuceneIT {

    private static final Logger LOG = LoggerFactory.getLogger(LuceneIT.class);
    public static final String INDEX_HOME_DIR = "solr";
    public static final String INDEX_DIR = "solr.index";

    @BeforeEach
    public void setUp() {
        deleteDirecotry(INDEX_HOME_DIR);
    }

    @AfterEach
    public void tearDown() {
        deleteDirecotry(INDEX_HOME_DIR);
    }

    @Test
    public void testAddData() throws IOException, ParseException {
        final Version version = LUCENE_8_8_2;
        LOG.debug("Version: {}", version);
        Analyzer analyzer = new StandardAnalyzer();
        Path path = FileSystems.getDefault().getPath(INDEX_HOME_DIR, INDEX_DIR);
        FSDirectory fsDirectory = FSDirectory.open(path);
        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        //IndexWriter is threadsafe
        try (IndexWriter indexWriter = new IndexWriter(fsDirectory, config)) {//Directory index = new RAMDirectory(); IndexWriter indexWriter = new IndexWriter(index, config);
            addDoc(indexWriter, "Lucene in Action", "193398817");
            addDoc(indexWriter, "Lucene for Dummies", "55320055Z");
            addDoc(indexWriter, "Managing Gigabytes", "55063554A");
            addDoc(indexWriter, "The Art of Computer Science", "9900333X");
        }

        //IndexReader is threadsafe
        IndexReader reader = DirectoryReader.open(MMapDirectory.open(path));// IndexReader reader = DirectoryReader.open(index); - for RAMDiectory
        IndexSearcher searcher = new IndexSearcher(reader);
        // TopDocs docs = searcher.search(q, hitsPerPage);
        // ScoreDoc[] hits = docs.scoreDocs;
        int hitsPerPage = 10;
        String querystr = "lucene";
        Query q = new QueryParser("title", analyzer).parse(querystr);
        TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, 100000);
        searcher.search(q, collector);
        ScoreDoc[] hits = collector.topDocs().scoreDocs;

        LOG.info("Found {} lucene hits.", hits.length);
        for (int i = 0; i < hits.length; ++i) {
            int docId = hits[i].doc;
            Document document = searcher.doc(docId);
            LOG.info("{}. ISBN: {}\tTitle:{}", (i + 1), document.get("isbn"), document.get("title"));
            if (i == 0) {
                assertEquals("Lucene in Action", document.get("title"));
            } else {
                assertEquals("Lucene for Dummies", document.get("title"));
            }
        }
        assertEquals(2, hits.length);
    }

    private static void addDoc(IndexWriter w, String title, String isbn) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("title", title, Field.Store.YES));
        doc.add(new StringField("isbn", isbn, Field.Store.YES));
        w.addDocument(doc);
    }

    public static void deleteDirecotry(final String name) {
        File directory = new File(name);
        if (directory.exists()) {
            try {
                delete(directory);
            } catch (IOException e) {
            }
        }
    }

    public static void delete(File f) throws IOException {
        if (f.isDirectory()) {
            for (File c : f.listFiles()) {
                delete(c);
            }
        }
        if (!f.delete()) {
        }
    }
}
