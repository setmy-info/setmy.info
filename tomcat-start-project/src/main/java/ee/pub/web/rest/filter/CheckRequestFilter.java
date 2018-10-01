package ee.pub.web.rest.filter;

import java.io.IOException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Response;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class CheckRequestFilter implements ClientRequestFilter {

    @Override
    public void filter(final ClientRequestContext requestContext) throws IOException {
        System.out.println("Hello World : filter 2!");
        if (requestContext.getHeaders().get("Client-Name") == null) {
            //requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST).entity("Client-Name header must be defined.").build());
            System.out.println("Client-Name header must be defined.");
        }
    }
}
