package info.setmy.postscript;

import info.setmy.postscript.complex.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

public class DocumentIT {

    private static final String STORAGE_FOLDER = "target";

    Document document;
    File targetFolder;

    @BeforeEach
    public void setUp() {
        document = PSData.newDocument();
        targetFolder = new File(STORAGE_FOLDER);
    }

    @Test
    public void psCreation() {
        var file = new File(targetFolder, "psCreation.ps");

        document.write(file);


    }
}
