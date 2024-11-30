package info.setmy.ipfs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class IPFSServiceIT {

    IPFSService ipfsService;

    @BeforeEach
    public void setUp() {
        ipfsService = IPFSService.getInstance();
        ipfsService.init();
    }

    @Test
    public void testIPFSService() {
        byte[] content = ipfsService.getContent("QmZhuP4Y7kjUwGrRWHbZ5k4YxCYGZkK2dHs81gAQW84Rmq");
        String contentAsString = new String(content, UTF_8);
        assertThat(contentAsString).isEqualTo(
            "Set my info (setmy.info).\r\n" +
                "Test file.\r\n"
        );
    }
}
