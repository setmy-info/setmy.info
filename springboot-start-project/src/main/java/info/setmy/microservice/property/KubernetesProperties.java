package info.setmy.microservice.property;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties("spring-boot-start-project.kubernetes")
public class KubernetesProperties {

    private String nodeNameSpace;
    private String nodeName;
    private String podName;
}
