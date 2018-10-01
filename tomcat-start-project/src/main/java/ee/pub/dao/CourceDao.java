package ee.pub.dao;

import ee.pub.model.Course;
import java.util.List;
import java.util.Objects;
import org.apache.log4j.Logger;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class CourceDao {

    private static final Logger LOG = Logger.getLogger(CourceDao.class);

    private List<Course> courses;

    public List<Course> findAllCourses() {
        LOG.debug("findAllCourses");
        return this.courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(final List<Course> courses) {
        this.courses = courses;
    }

    public Course findById(final Long id) {
        for (Course cour : courses) {
            if (Objects.equals(cour.getId(), id)) {
                return cour;
            }
        }
        return null;
    }
}
