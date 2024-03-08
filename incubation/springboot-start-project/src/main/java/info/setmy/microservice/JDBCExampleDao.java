package info.setmy.microservice;

import jakarta.inject.Named;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Log4j2
@Named("jdbcExampleDao")
public class JDBCExampleDao extends JDBCBaseDao implements RowMapper<ExampleModel>, IExampleDao {

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
        final List<ExampleModel> examples = jdbcTemplate.query(FIND_BY_TEXT, this, placeholders(searchText));
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
