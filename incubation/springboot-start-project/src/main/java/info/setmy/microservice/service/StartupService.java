package info.setmy.microservice.service;

import info.setmy.microservice.property.BuildProperties;
import info.setmy.microservice.property.MavenProjectProperties;
import info.setmy.microservice.property.StartupProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import static java.util.TimeZone.getTimeZone;
import static java.util.TimeZone.setDefault;

@Service
@RequiredArgsConstructor
public class StartupService {

    private final Logger log = LogManager.getLogger(getClass());

    private final BuildProperties buildProperties;

    private final MavenProjectProperties mavenProjectProperties;

    private final StartupProperties startupProperties;

    protected final StartupVerificationService startupVerificationService;

    @PostConstruct
    public void init() {
        logInfo();
        startupVerificationService.preVerify();
        setDefault(getTimeZone(startupProperties.getTimeZone()));
        startupVerificationService.postVerify();
    }

    private void logInfo() {
        log.info("===========================");
        log.info("buildProperties: {}", buildProperties);
        log.info("mavenProjectProperties: {}", mavenProjectProperties);
    }
}
