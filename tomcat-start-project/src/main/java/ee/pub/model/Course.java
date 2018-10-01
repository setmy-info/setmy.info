package ee.pub.model;

import java.io.Serializable;
import java.util.List;

/**
 * Course class.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Course implements Serializable {

    private Long id;
    private String name;
    private List<Person> students;

    /**
     * @return the students
     */
    public List<Person> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(final List<Person> students) {
        this.students = students;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }
}
