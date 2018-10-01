package ee.pub.dao;

import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * SpringJDBCBaseDao class.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class SpringJDBCBaseDao {

    @Inject
    protected JdbcTemplate jdbcTemplate;

    protected StringBuilder builder(final String... parameters) {
        final StringBuilder sb = new StringBuilder();
        for (final String param : parameters) {
            sb.append(param);
        }
        return sb;
    }

    protected Object[] placeholders(final Object... parameters) {
        return parameters;
    }
}
