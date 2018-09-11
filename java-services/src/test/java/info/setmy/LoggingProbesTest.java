package info.setmy;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingProbesTest {

    static final Logger LOG = LoggerFactory.getLogger(LoggingProbesTest.class);

    @Test
    public void logging() {
        LOG.trace("Hello World {}!", "from me");
        LOG.debug("How are {} today?", "you");
        LOG.info("I am fine.");
        LOG.warn("I love programming.");
        LOG.error("I am programming.");
    }
}
