package info.setmy.stealer.cli.models;

import info.setmy.stealer.cli.StealerConfigService;
import info.setmy.stealer.services.StealerService;
import info.setmy.stealer.services.StealerServiceIT;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static java.lang.System.getProperty;
import static java.lang.System.setProperty;

public class StealerCallableIT {

    public static final String TARGET_DIR = "target/";
    public static final String TEST_DATA_DIR = TARGET_DIR + "test-classes/";
    public static final String STEALER_TEST_WORKING_DIR = TEST_DATA_DIR + StealerServiceIT.class.getSimpleName();

    private File originalDir;
    private File testDir;
    private String[] arguments;

    private StealerCallable stealerCallable;
    private StealerService stealerService;
    private StealerConfigService stealerConfigService;

    @BeforeEach
    void setUp() {
        stealerService = new StealerService();
        stealerConfigService = new StealerConfigService();
        originalDir = new File(getProperty("user.dir"));
        testDir = new File(STEALER_TEST_WORKING_DIR);
        setProperty("user.dir", testDir.getAbsolutePath());
        arguments = new String[]{};
        stealerCallable = new StealerCallable(stealerService, stealerConfigService);
    }

    @AfterEach
    void tearDown() {
        setProperty("user.dir", originalDir.getAbsolutePath());
    }

    @Test
    public void call() throws Exception {
        Integer result = stealerCallable.call();
    }
}
