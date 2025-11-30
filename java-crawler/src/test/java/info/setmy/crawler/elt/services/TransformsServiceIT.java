package info.setmy.crawler.elt.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;

import static info.setmy.crawler.camel .CamelIT.BASE_UNIXES_DIR;
import static info.setmy.crawler.camel.CamelIT.BASE_WINDOWS_DIR;

class TransformsServiceIT {

    String homeDirectoryNameString;
    String workingDirectoryNameString;
    File homeDirectoryFile;
    File workingDirectoryFile;
    GuiceService guiceService;
    TransformsService transformsService;

    @BeforeEach
    void setUp() {
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            homeDirectoryNameString = BASE_WINDOWS_DIR;
            workingDirectoryNameString = BASE_WINDOWS_DIR;
        } else {
            homeDirectoryNameString = BASE_UNIXES_DIR;
            workingDirectoryNameString = BASE_UNIXES_DIR;
        }
        homeDirectoryFile = new File(homeDirectoryNameString);
        workingDirectoryFile = new File(workingDirectoryNameString);
        guiceService = new GuiceService(homeDirectoryFile, workingDirectoryFile)
            .init();
        transformsService = guiceService.getInjector().getInstance(TransformsService.class);
    }

    @Test
    @Disabled
    void csvToDb() {
        transformsService.csvToDb();
    }
}
