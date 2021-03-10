package info.setmy.lucene;

import info.setmy.exceptions.InitializationException;
import info.setmy.exceptions.UncheckedException;
import info.setmy.models.Paging;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
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
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import static org.apache.lucene.util.Version.LUCENE_8_8_1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class LuceneService {

    static final Logger LOG = LoggerFactory.getLogger(LuceneService.class);

    private final Version version = LUCENE_8_8_1;

    private final String locationDirectory;

    private final String indexDirectory;

    private Path path;

    private Directory directory;

    private Analyzer analyzer;

    private IndexWriterConfig config;

    private IndexWriter indexWriter;//IndexWriter is threadsafe

    /**
     * Constructor to create RAM based DB.
     */
    public LuceneService() {
        this.locationDirectory = null;
        this.indexDirectory = null;
    }

    /**
     * Constructor to create storage based DB.
     *
     * @param locationDirectory is DB location.
     * @param indexDirectory is DB index location.
     */
    public LuceneService(final String locationDirectory, final String indexDirectory) {
        this.locationDirectory = locationDirectory;
        this.indexDirectory = indexDirectory;
    }

    public void init() {
        LOG.info("Lusene versionn: {}", version);
        try {
            initDirectory();
            analyzer = new StandardAnalyzer();
            config = new IndexWriterConfig(analyzer);
            initWriter();
        } catch (IOException ex) {
            LOG.error("Lucene initialization error: {}", ex);
            throw new InitializationException("Lucene initialization error: ", ex);
        }
    }

    private void initDirectory() throws IOException {
        if (isFileSystemDB()) {
            path = FileSystems.getDefault().getPath(locationDirectory, indexDirectory);
            directory = FSDirectory.open(path);
        } else {
            directory = new RAMDirectory();
        }
    }

    private void initWriter() throws IOException {
        indexWriter = new IndexWriter(directory, config);
        indexWriter.commit();
    }

    private boolean isFileSystemDB() {
        return locationDirectory != null && indexDirectory != null;
    }

    public long write(final info.setmy.lucene.Document doc) throws IOException {
        return write(doc.getLuceneDocument());
    }

    public long write(final Document document) throws IOException {
        final long result = indexWriter.addDocument(document);
        indexWriter.commit();
        return result;
    }

    public <T> List<T> query(final String queryString, final String fieldName, final Paging paging, final Class<T> clazz) {
        final List<T> result = new ArrayList<>();
        IndexReader reader = null;
        try {
            final Query query = new QueryParser(fieldName, analyzer).parse(queryString);
            reader = getReader();
            final IndexSearcher indexSearcher = new IndexSearcher(reader);
            final TopScoreDocCollector collector = TopScoreDocCollector.create(paging.getResultsOnPage(), 1000000);
            indexSearcher.search(query, collector);
            final ScoreDoc[] hits = collector.topDocs().scoreDocs;
            paging.setResults(hits.length);
            for (ScoreDoc hit : hits) {
                int docId = hit.doc;
                Document document = indexSearcher.doc(docId);
                info.setmy.lucene.Document resultDocument = toDocument(document, clazz);
                result.add((T) resultDocument);
            }
        } catch (ParseException | IOException ex) {
            LOG.error("Error with querying data from Lucene {}", ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    LOG.error("Error with closing Lucene reader {}", ex);
                }
            }
        }
        return result;
    }

    private IndexReader getReader() throws IOException {
        IndexReader reader;
        if (isFileSystemDB()) {
            reader = DirectoryReader.open(MMapDirectory.open(path));
        } else {
            reader = DirectoryReader.open(indexWriter);
        }
        return reader;
    }

    private <T> info.setmy.lucene.Document toDocument(final Document document, final Class<T> clazz) {
        try {
            final Constructor<?> ctor = clazz.getConstructor(org.apache.lucene.document.Document.class);
            final Object classInstance = ctor.newInstance(new Object[]{document});
            return (info.setmy.lucene.Document) classInstance;
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new UncheckedException("Creating class for document has an error", ex);
        }
    }

    public void close() {
        try {
            indexWriter.flush();
            indexWriter.close();
        } catch (IOException ex) {
            LOG.error("Lucene index writer closing has an derror {}", ex);
            throw new UncheckedException("Lucene closing error", ex);
        }
    }
}
