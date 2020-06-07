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
import org.springframework.core.env.Environment;
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
    private final static String PORT = "port";
    private final static String JAVA_HOME = "javaHome";
    private final static String JAVA_VERSION = "javaVersion";
    private final static String PROFILES = "profiles";

    private final BuildProperties buildProperties;

    private final MavenProjectProperties mavenProjectProperties;

    private final Environment environment;

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
        microserviceInfo.put(PORT, environment.getProperty("server.port"));
        microserviceInfo.put(JAVA_HOME, environment.getProperty("JAVA_HOME"));
        microserviceInfo.put(JAVA_VERSION, environment.getProperty("java.version"));
        try {
            microserviceInfo.put(HOST_NAME, InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException ex) {
            log.error("Can not find hostname", ex);
        }
        final StringBuilder stringBuilder = new StringBuilder();
        for (String profileName : environment.getActiveProfiles()) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(profileName);
        }
        microserviceInfo.put(PROFILES, stringBuilder.toString());
        microserviceInfo = Collections.unmodifiableMap(microserviceInfo);
        log.info(
                "Microservice '{}:{}:{}', host: '{}', port: {}, timestamp: '{}', SCM revision: '{}', javaHome: '{}', javaVersion: '{}' profiles: '{}'",
                mavenProjectProperties.getGroupId(),
                mavenProjectProperties.getArtifactId(),
                buildProperties.getVersion(),
                microserviceInfo.get(HOST_NAME),
                microserviceInfo.get(PORT),
                buildProperties.getTimestamp(),
                buildProperties.getRevision(),
                microserviceInfo.get(JAVA_HOME),
                microserviceInfo.get(JAVA_VERSION),
                microserviceInfo.get(PROFILES)
        );
    }
}
