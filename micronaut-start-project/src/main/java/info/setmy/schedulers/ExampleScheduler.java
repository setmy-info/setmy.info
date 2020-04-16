package info.setmy.schedulers;

import io.micronaut.scheduling.annotation.Scheduled;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class ExampleScheduler {

    private final Logger log = LoggerFactory.getLogger(ExampleScheduler.class);

    @Scheduled(fixedDelay = "60s")
    public void importMessages() {
        log.info("Starting scheduled task");

        log.info("Finished scheduled task");
    }
}
