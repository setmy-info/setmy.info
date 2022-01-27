package info.setmy;

import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingProbesTest {

    final Logger log = LogManager.getLogger(this.getClass());

    @Test
    public void logging() {
        log.trace("Hello World {}!", "from me");
        log.debug("How are {} today?", "you");
        log.info("I am fine.");
        log.warn("I love programming.");
        log.error("I am programming.");
    }
}
