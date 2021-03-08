package info.setmy.microservice.components;

import info.setmy.microservice.exceptions.MicroServiceException;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named("personTransactionErrorComponent")
public class PersonTransactionErrorComponent {

    final Logger log = LogManager.getLogger(getClass());

    private boolean shouldFail = false;

    public void toggle() {
        shouldFail = !shouldFail;
        if (shouldFail) {
            log.info("SHOULD FAIL!");
        } else {
            log.info("Non failing!");
        }
    }

    public void errorOnDemand() {
        if (shouldFail) {
            throw new MicroServiceException("SHOULD FAIL");
        }
    }
}
