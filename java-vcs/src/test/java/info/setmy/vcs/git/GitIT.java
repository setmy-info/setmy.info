package info.setmy.vcs.git;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class GitIT {

    private Git gitA;
    private Git gitB;

    private String URL_A = "git@github.com:setmy-info/stealer-test-a.git";
    private String URL_B = "git@github.com:setmy-info/stealer-test-b.git";
    private String DIR = "./target";

    @BeforeEach
    public void before() throws IOException {
        FileUtils.deleteDirectory(new File("./target/stealer-test-a.git"));
        FileUtils.deleteDirectory(new File("./target/stealer-test-b.git"));
        gitA = new Git(URL_A, DIR);
        gitB = new Git(URL_B, DIR);
    }

    @Test
    @Disabled
    @DisplayName("manual execution test for cloning and branch change")
    public void clone_and_update() {
        gitA.doClone();
        gitA.doCheckout("master");

        gitB.doClone();
        gitB.doCheckout("develop");
    }
}
