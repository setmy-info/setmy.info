package info.setmy.tika;

import info.setmy.models.storage.Storage;
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

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
        System.out.println("[DEBUG_LOG] Starting test()");
        System.out.flush();
        try {
            final TikaConfig tika = new TikaConfig();
            for (File file : storage.listStorageFiles(".")) {
                Metadata metadata = new Metadata();
                metadata.set(Metadata.TIKA_MIME_FILE, file.toString());
                String mimetype = tika.getDetector().detect(TikaInputStream.get(storage.getStorageFileStream(file.getName()).get()), metadata).toString();
                System.out.println("[DEBUG_LOG] File " + file + " is " + mimetype);
                System.out.flush();
            }
            assertContent(DOC);
            assertContent(PDF);
            assertContent(ODT);
            assertContent(DOCX);
        } catch (Throwable t) {
            System.out.println("[DEBUG_LOG] Exception in test(): " + t.getClass().getName() + ": " + t.getMessage());
            t.printStackTrace(System.out);
            System.out.flush();
            throw t;
        }
    }

    private void assertContent(final String fileName) throws TikaException, IOException, SAXException {
        final String content = parseToStringExample(fileName);
        assertThat(content)
            .contains("Lorem ipsum")
            .contains("Example document.")
            .contains("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse vitae maximus odio.")
            .contains("Suspendisse orci dui, tincidunt ut lectus vitae, iaculis blandit nisi. Sed ut accumsan sem.");
    }

    private String parseToStringExample(final String fileName) throws IOException, SAXException, TikaException {
        Tika tika = new Tika();
        log.info("[DEBUG_LOG] Tika parsers: {}", tika.getParser());
        try (InputStream stream = storage.getStorageFileStream(fileName).get()) {
            String content = tika.parseToString(stream);
            log.info("[DEBUG_LOG] Parsed {} content (length: {}): >>{}<<", fileName, content.length(), content);
            return content;
        }
    }
}
