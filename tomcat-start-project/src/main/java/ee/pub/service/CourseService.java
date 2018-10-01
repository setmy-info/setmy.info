package ee.pub.service;

import ee.pub.dao.CourceDao;
import ee.pub.model.Course;
import ee.pub.model.Person;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class CourseService {

    private static final Logger LOG = Logger.getLogger(CourseService.class);

    private CourceDao courseDao;

    public List<Course> findAllCourses() {
        return this.courseDao.findAllCourses();
    }

    public Course findById(Long id) {
        return this.courseDao.findById(id);
    }

    public Person makePerson(final String firstName, final String lastName) {
        Person ret = new Person();
        ret.setFirstName(firstName);
        ret.setLastName(lastName);
        return ret;
    }

    public Person getPerson(Long id) {
        Person ret = null;
        return ret;
    }

    public Person addPerson(Person person) {
        Person ret = null;
        return ret;
    }

    public List<Person> getAllPersons() {
        List<Person> ret = new ArrayList<>();
        return ret;
    }

    public CourceDao getCourseDao() {
        return courseDao;
    }

    public void setCourseDao(CourceDao courseDao) {
        this.courseDao = courseDao;
    }
}
