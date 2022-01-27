package info.setmy.cve;

import java.io.IOException;
import java.net.ServerSocket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class LoggingProblemIT {

    final Logger log = LogManager.getLogger(this.getClass());

    private final String PAYLOAD = "${jndi:ldap://localhost:6868}";

    private Thread thread;
    private ServerSocket serverSocket;
    private boolean isConnected;

    @BeforeEach
    public void before() throws IOException {
        thread = new Thread(() -> {
            try {
                serverThread();
            } catch (IOException ex) {
            }
        });
        thread.start();
        sleep(1000);
        log.info("Before is done");
    }

    @AfterEach
    public void after() throws InterruptedException {
        thread.join();
    }

    @Test
    @Timeout(3) // Getting timeout means same as ther is CVE-2021-44228. Should not get timeout, if log4j is not connecting anywhere.
    public void logging() throws IOException {
        log.info("CVE-2021-44228");
        log.info(PAYLOAD);
        sleep(1000);
        assertThat(isConnected).isFalse();
        serverSocket.close();
    }

    private void serverThread() throws IOException {
        log.info("server starting");
        serverSocket = new ServerSocket(6868);
        serverSocket.accept();
        log.info("server connected");
        isConnected = true;
    }

    protected void sleep(final long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
