package info.setmy.microservice;

import jakarta.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;

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
