package info.setmy.microservice.config;

import info.setmy.microservice.rest.ExampleRest;
import info.setmy.microservice.universum.rest.UniversumRest;
import org.springframework.stereotype.Component;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Component
@ApplicationPath("/rest")
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        register(ExampleRest.class);
        register(UniversumRest.class);
    }
}
