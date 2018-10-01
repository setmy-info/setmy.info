package ee.pub.web.actions;

import com.opensymphony.xwork2.ActionSupport;
import ee.pub.service.CourseService;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BaseAction extends ActionSupport {

    private CourseService courseService;

    private Request request = new Request();

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public CourseService getCourseService() {
        return courseService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }
}
