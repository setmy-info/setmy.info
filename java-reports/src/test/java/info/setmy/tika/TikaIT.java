package info.setmy.tika;

import info.setmy.models.storage.Storage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

/**
 * https://tika.apache.org/1.18/detection.html
 * https://tika.apache.org/1.18/examples.html
 * https://tika.apache.org/1.18/examples.html#Parsing_using_the_Tika_Facade
 */
public class TikaIT {

    private Storage storage;
    private final Logger log = LogManager.getLogger(this.getClass());

    private final String DOC = "Lorem-impsum.doc";
    private final String PDF = "Lorem-impsum.pdf";
    private final String ODT = "Lorem-impsum.odt";
    private final String DOCX = "Lorem-impsum.docx";

    @BeforeEach
    public void before() {
        storage = new Storage("target/test-classes/docs");
        storage.init();
    }

    @Test
    public void test() throws TikaException, IOException, SAXException {
        final TikaConfig tika = new TikaConfig();
        for (File file : storage.listStorageFiles(".")) {
            Metadata metadata = new Metadata();
            metadata.set(Metadata.RESOURCE_NAME_KEY, file.toString());
            String mimetype = tika.getDetector().detect(TikaInputStream.get(storage.getStorageFileStream(file.getName()).get()), metadata).toString();
            log.debug("File {} is {}", file, mimetype);
        }
        log.debug("DOC content: {}", parseToStringExample(DOC));
        log.debug("PDF content: {}", parseToStringExample(PDF));
        log.debug("ODT content: {}", parseToStringExample(ODT));
        log.debug("DOCX content: {}", parseToStringExample(DOCX));
    }

    private String parseToStringExample(final String fileName) throws IOException, SAXException, TikaException {
        Tika tika = new Tika();
        try (InputStream stream = storage.getStorageFileStream(fileName).get()) {
            return tika.parseToString(stream);
        }
    }
}
