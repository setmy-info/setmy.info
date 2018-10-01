package ee.pub.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class WebFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(WebFilter.class);
    private FilterConfig filterConfig = null;

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        LOG.info("============== WebFilter init =====================");
        filterConfig.getServletContext().setAttribute("hitCounter", new Counter());
        this.setFilterConfig(filterConfig);
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        if (filterConfig == null) {
            LOG.info("============== config is null =====================");
            return;
        }
        final Counter counter = (Counter) filterConfig.getServletContext().getAttribute("hitCounter");
        LOG.info("");
        LOG.info("===============");
        LOG.info("The number of hits is: " + counter.incCounter());
        LOG.info("===============");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    static public class Counter {

        int counter = 0;

        public Integer incCounter() {
            this.counter++;
            return this.counter;
        }
    }
}
