package info.setmy.crawler.elt.services;

import info.setmy.crawler.elt.models.GuiceCreation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;

import static info.setmy.crawler.camel.CamelIT.BASE_UNIXES_DIR;
import static info.setmy.crawler.camel.CamelIT.BASE_WINDOWS_DIR;

class ScvDbServiceIT {

    String homeDirectoryNameString;
    String workingDirectoryNameString;
    File homeDirectoryFile;
    File workingDirectoryFile;
    GuiceService guiceService;
    ScvDbService scvDbService;

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
        guiceService = GuiceService.newGuiceService(
                GuiceCreation.builder()
                    .homeDirectory(homeDirectoryFile)
                    .workingDirectory(workingDirectoryFile)
                    .build()
            )
            .init();
        scvDbService = guiceService.getInjector().getInstance(ScvDbService.class);
    }

    @Test
    @Disabled
    void run() {
        scvDbService.run();
    }
}
