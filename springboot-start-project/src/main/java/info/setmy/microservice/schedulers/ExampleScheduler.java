package info.setmy.microservice.schedulers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Component("exampleScheduler")
public class ExampleScheduler {

    final Logger log = LogManager.getLogger(ExampleScheduler.class);

    @Scheduled(fixedDelayString = "${scheduler.timeInMillis}")
    public void importMessages() {
        log.info("Starting scheduled task");

        log.info("Finished scheduled task");
    }
}
