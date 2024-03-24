package info.setmy.microservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ExampleScheduler {

    @Scheduled(fixedDelay = 30000)
    public void scheduledTaskForFirstDB() {
        log.info("Scheduled");
    }
}
