package info.setmy.microservice.schedulers;

import info.setmy.microservice.components.PersonTransactionErrorComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Component("personTransactionScheduler")
public class PersonTransactionScheduler {

    final Logger log = LogManager.getLogger(getClass());

    private final PersonTransactionErrorComponent personTransactionErrorComponent;

    public PersonTransactionScheduler(final PersonTransactionErrorComponent personTransactionErrorComponent) {
        this.personTransactionErrorComponent = personTransactionErrorComponent;
    }

    @Scheduled(fixedDelayString = "${scheduler.transactionTimeInMillis}")
    public void importMessages() {
        log.info("Start toggle transaction errors");
        personTransactionErrorComponent.toggle();
    }
}
