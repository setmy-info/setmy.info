package info.setmy.ipfs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IPFSServiceIT {

    IPFSService ipfsService;

    @BeforeEach
    public void setUp() {
        ipfsService = IPFSService.getInstance();
        ipfsService.init();
    }

    @Test
    public void asString() {
        String contentAsString = ipfsService.asString("QmZhuP4Y7kjUwGrRWHbZ5k4YxCYGZkK2dHs81gAQW84Rmq");
        assertThat(contentAsString).isEqualTo(
            "Set my info (setmy.info).\r\n" +
                "Test file.\r\n"
        );
    }

    @Test
    @Disabled
    public void addFile() {
        // Added : "QmaZcQpDQKmC8cBqkCZF4n84cJXmiQumPCfL7bDTSWEJZx"
        String hash = ipfsService.addFile("./src/test/resources/java.ipfs.txt");
        System.out.println("HASH 2:" + hash);
    }

    @Test
    public void asString2() {
        String contentAsString = ipfsService.asString("QmaZcQpDQKmC8cBqkCZF4n84cJXmiQumPCfL7bDTSWEJZx");
        assertThat(contentAsString).isEqualTo(
            "Set my info (setmy.info).\n" +
                "Java test file - java.ipfs.txt.\n"
        );
    }
}
