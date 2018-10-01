package ee.pub.web.rest.filter;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class RequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {
        System.out.println("Hello World : filter 3!");
        final SecurityContext securityContext = requestContext.getSecurityContext();
        if (securityContext == null || !securityContext.isUserInRole("privileged")) {
            //requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("User cannot access the resource.").build());
            System.out.println("User cannot access the resource!");
        }
    }
}
