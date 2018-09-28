package info.setmy.microservice.universum.rest;

import static info.setmy.microservice.universum.constants.MeasuringUnits.SCALE;
import info.setmy.microservice.universum.models.CelestialObject;
import info.setmy.microservice.universum.services.UniversumService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Lessons Jersey rest interface about universum scale calculation.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Component("universumRest")
@Path("/universum")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UniversumRest {

    final Logger log = LogManager.getLogger(this.getClass());

    private final UniversumService universumService;

    @Inject
    public UniversumRest(final UniversumService universumService) {
        this.universumService = universumService;
    }

    /**
     * REST call GET : http://localhost:8080/rest/universum/1
     * 
     * @param scale to calculate
     * @return planets list in scaled.
     */
    @GET
    @Path("/{scale}")
    @Produces(APPLICATION_JSON)
    public List<CelestialObject> scale(@PathParam("scale") final long scale) {
        return universumService.getScaled(scale);
    }
    
    // REST Call GET : http://localhost:8080/rest/universum
    @GET
    @Produces(APPLICATION_JSON)
    public List<CelestialObject> scale() {
        return universumService.getScaled(SCALE);
    }
}
