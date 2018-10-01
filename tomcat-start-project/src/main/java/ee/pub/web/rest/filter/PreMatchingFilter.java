package ee.pub.web.rest.filter;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@PreMatching
public class PreMatchingFilter implements ContainerRequestFilter {

    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {
        System.out.println("Hello World : filter 1!");
        // change all PUT methods to POST
        if (requestContext.getMethod().equals("PUT")) {
            //requestContext.setMethod("POST");
            System.out.println("PUT to POST!");
        }
    }
}
