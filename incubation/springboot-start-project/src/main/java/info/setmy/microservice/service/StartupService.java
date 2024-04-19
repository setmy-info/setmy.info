package info.setmy.microservice.service;

import info.setmy.microservice.property.BuildProperties;
import info.setmy.microservice.property.MavenProjectProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StartupService {

    private final Logger log = LogManager.getLogger(getClass());

    private final BuildProperties buildProperties;

    private final MavenProjectProperties mavenProjectProperties;

    @PostConstruct
    public void inti() {
        log.info("===========================");
        log.info("buildProperties: {}", buildProperties);
        log.info("mavenProjectProperties: {}", mavenProjectProperties);
    }
}
