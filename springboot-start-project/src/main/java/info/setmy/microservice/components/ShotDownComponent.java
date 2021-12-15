package info.setmy.microservice.components;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Log4j2
@Component
@RequiredArgsConstructor
public class ShotDownComponent {

    @PreDestroy
    public void preDestroy() throws Exception {
        log.info("======== IS GETTING SHUT DOWN ===========");
    }
}
