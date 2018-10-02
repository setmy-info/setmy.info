package info.setmy.microservice.dao;

import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JDBC specific DAO class.
 *
 * https://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class JDBCBaseDao {

    @Inject
    protected JdbcTemplate jdbcTemplate;

    protected StringBuilder builder(String... parameters) {
        final StringBuilder sb = new StringBuilder();
        for (String param : parameters) {
            sb.append(param);
        }
        return sb;
    }

    protected Object[] placeholders(Object... parameters) {
        return parameters;
    }
}
