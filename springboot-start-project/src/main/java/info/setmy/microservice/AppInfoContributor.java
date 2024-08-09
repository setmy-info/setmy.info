package info.setmy.microservice;

import info.setmy.microservice.property.BuildProperties;
import info.setmy.microservice.property.MavenProjectProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppInfoContributor implements InfoContributor {

    private final BuildProperties buildProperties;

    private final MavenProjectProperties mavenProjectProperties;

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("groupId", mavenProjectProperties.getGroupId());
        builder.withDetail("artifactId", mavenProjectProperties.getArtifactId());
        builder.withDetail("revision", buildProperties.getRevision());
        builder.withDetail("timestamp", buildProperties.getTimestamp());
    }
}
