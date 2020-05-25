package info.setmy.microservice.services;

import info.setmy.microservice.properties.BuildProperties;
import info.setmy.microservice.properties.MavenProjectProperties;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Service;

/**
 * https://docs.spring.io/spring-boot/docs/2.3.x/actuator-api/html/
 *
 * http://localhost:8080/actuator/info
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Service("mavenService")
@RequiredArgsConstructor
@Log4j2
public class InfoService implements InfoContributor {

    private final static String HOST_NAME = "hostname";

    private final BuildProperties buildProperties;

    private final MavenProjectProperties mavenProjectProperties;

    private Map<String, String> microserviceInfo;

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("microserviceInfo", microserviceInfo);
    }

    @PostConstruct
    public void init() {
        microserviceInfo = new HashMap<>();
        microserviceInfo.put("groupId", mavenProjectProperties.getGroupId());
        microserviceInfo.put("artifactId", mavenProjectProperties.getArtifactId());
        microserviceInfo.put("version", buildProperties.getVersion());
        microserviceInfo.put("timestamp", buildProperties.getTimestamp());
        microserviceInfo.put("revision", buildProperties.getRevision());
        try {
            microserviceInfo.put(HOST_NAME, InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException ex) {
            log.error("Can not find hostname", ex);
        }
        microserviceInfo = Collections.unmodifiableMap(microserviceInfo);
        log.info(
                "Microservice '{}:{}:{}', host: '{}', timestamp: '{}', SCM revision: '{}'",
                mavenProjectProperties.getGroupId(),
                mavenProjectProperties.getArtifactId(),
                buildProperties.getVersion(),
                microserviceInfo.get(HOST_NAME),
                buildProperties.getTimestamp(),
                buildProperties.getRevision()
        );
    }
}
