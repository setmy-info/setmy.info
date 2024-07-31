package info.setmy.stealer.services;

import info.setmy.stealer.models.StealerConfig;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class StealerServiceIT {

    public static final String TARGET_DIR = "./target";
    public static final String TEST_DATA_DIR = TARGET_DIR + "/test-data";
    public static final String STEALER_DIR = TEST_DATA_DIR + "/.stealer";

    StealerService stealerService;

    StealerConfig stealerConfig;

    @BeforeEach
    public void setUp() throws IOException {
        stealerService = new StealerService();
        FileUtils.deleteDirectory(new File(TEST_DATA_DIR));
    }

    @Test
    public void testStealerService() {
        stealerConfig = StealerConfig.builder()
            .workingDirectory(new File(STEALER_DIR))
            .build();

        stealerService.steal(stealerConfig);

    }
}

