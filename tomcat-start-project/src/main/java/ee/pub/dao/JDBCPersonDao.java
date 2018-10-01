package ee.pub.dao;

import ee.pub.model.Person;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
//import javax.inject.Named;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

/**
 * JDBCPersonDao class.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
//@Named("personDao")
public class JDBCPersonDao extends SpringJDBCBaseDao implements RowMapper<Person>, IPersonDao {

    private static final Logger LOG = Logger.getLogger(JDBCPersonDao.class);

    static final String SEQUENCE_SQL = "SELECT nextval('person_id_seq')";

    static final String INSERT_SQL = "insert into person (id, first_name, last_name, age) values(?, ?, ?, ?)";

    static final String FIND_BY_ID = "select * from person where id= ?";

    static final String FIND_ALL = "select * from person order by id";

    static final String UPDATE = "update todo set first_name = ?, last_name = ?, age = ? where id = ?";

    static final String DELETE = "delete from person where id = ?";

    @Override
    public Person mapRow(final ResultSet row, final int rowNumber) throws SQLException {
        final Person todo = new Person();
        todo.setId(row.getLong("id"));
        todo.setFirstName(row.getString("first_name"));
        todo.setLastName(row.getString("last_name"));
        return todo;
    }

    @Override
    public Person save(final Person person) {
        final String personMessage = String.format(
                "Saving person firstName=%s, lastName=%s",
                person.getFirstName(), person.getLastName());
        LOG.debug(personMessage);
        final Long nextValue = jdbcTemplate.queryForObject(SEQUENCE_SQL, Long.class);
        final int rowsInserted = jdbcTemplate.update(INSERT_SQL,
                nextValue, person.getFirstName(), person.getLastName(), person.getAge());
        if (rowsInserted == 1) {
            return person;
        }
        final String errorMessage = personMessage + " is not possible!";
        LOG.error(errorMessage);
        throw new RuntimeException(errorMessage);
    }

    @Override
    public Person find(final Long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, placeholders(id), this);
    }

    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query(FIND_ALL, this);
    }

    @Override
    public Person update(final Person person) {
        jdbcTemplate.update(UPDATE,
                placeholders(
                        person.getFirstName(),
                        person.getLastName(),
                        person.getAge(),
                        person.getId()
                )
        );
        return person;
    }

    @Override
    public void delete(final Long id) {
        jdbcTemplate.update(DELETE, id);
    }
}
