package ee.pub.web.actions;

import ee.pub.model.Course;
import ee.pub.model.Person;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class CourseAction extends BaseAction {

	private static final Logger LOG = Logger.getLogger(CourseAction.class);
	//--- After logic ----------
	private List<Course> coursesList;
	private Course course;
	private Person person;

	@Override
	public String execute() throws Exception {
		//Executed for any action page - when no  method=in struts.xml 
		LOG.debug("CourseAction.execute() START!");
		this.setCoursesList(this.getCourseService().findAllCourses());
		String ret = this.choseFlow(this.getRequest());
		LOG.debug("CourseAction.execute() END!");
		return ret;
	}

	@Override
	public void validate() {
		//Validates execute()
		LOG.debug("CourseAction.validate()");
	}

	public String list() {
		return SUCCESS;
	}

	/**
	 * @return the coursesList
	 */
	public List<Course> getCoursesList() {
		return coursesList;
	}

	/**
	 * @param coursesList the coursesList to set
	 */
	public void setCoursesList(List<Course> coursesList) {
		this.coursesList = coursesList;
	}

	private String choseFlow(Request req) {
		String ret;
		if (req.getId() != null) {
			ret = this.idRequest(req);
		} else if (req.getFromResult() != null || req.getResultsNumber() != null) {
			ret = this.fromTo(req);
		} else {
			ret = this.enterPage(req);
		}
		return ret;
	}

	// All null - empty or ntering first time page.
	private String enterPage(Request req) {
		String ret = "success";
		LOG.debug("Entering page!");
		return ret;
	}

	// Requested results from.
	private String fromTo(Request req) {
		String ret = "success";
		LOG.debug("From - On Page - To!");
		return ret;
	}

	// Requested concrete element.
	private String idRequest(Request req) {
		String ret = "success";
		LOG.debug("ID Request!");
		Long id = 1L;
		this.course = this.getCourseService().findById(id);
		return ret;
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}
}
