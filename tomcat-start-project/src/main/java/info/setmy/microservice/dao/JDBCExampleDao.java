package info.setmy.microservice.dao;

import info.setmy.microservice.models.ExampleModel;
import info.setmy.microservice.rest.ExampleRest;
import java.util.List;

import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * https://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Named("jdbcExampleDao")
public class JDBCExampleDao extends JDBCBaseDao implements RowMapper<ExampleModel>, IExampleDao {

    final Logger log = LogManager.getLogger(ExampleRest.class);

    //final String INSERT_SQL = "insert into example (text) values(?)"; // Pg autoincrement
    final String INSERT_SQL = "insert into example (id, text) values(?, ?)";

    final String FIND_BY_TEXT = "select * from example where text like ?";

    @Override
    public ExampleModel save(final ExampleModel example) {
        log.debug("SAVE EXAMPLE");
        final String text = example.getText();
        final Long id = example.getId();// Not needed with autoincrement DB!
        //final int rows = jdbcTemplate.update(INSERT_SQL, text);
        final int rows = jdbcTemplate.update(INSERT_SQL, id, text);
        if (rows == 1) {
            final List<ExampleModel> list = this.findByText(text);
            final ExampleModel savedExample = list.get(0);
            return savedExample;
        }
        log.error("Saving EXAMPLE is not possible!");
        throw new RuntimeException("Saving EXAMPLE is not possible!");
    }

    public List<ExampleModel> findByText(final String exampleText) {
        log.debug("FIND BY TEXT");
        final String searchText = builder("%", exampleText, "%").toString();
        final List<ExampleModel> examples = jdbcTemplate.query(FIND_BY_TEXT, placeholders(searchText), this);
        return examples;
    }

    @Override
    public ExampleModel mapRow(final ResultSet row, final int rowNum) throws SQLException {
        final ExampleModel example = new ExampleModel();
        example.setId(row.getLong("id"));
        example.setText(row.getString("text"));
        return example;
    }
}
