package ee.pub.model;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.mockito.BDDMockito;
import static org.mockito.Mockito.mock;

/**
 *
 * @author Imre Tabur <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class CourseDefinitions {

    Course course;
    List<Person> students;
    List<Person> expectedSudentsList;

    @Before
    public void beforeScenario() {
        students = null;
        course = null;
    }

    @Given("^newly created course object$")
    public void newly_created_course_object() throws Throwable {
        course = mock(Course.class);
        expectedSudentsList = new ArrayList<>();
        BDDMockito.given(course.getStudents()).willReturn(expectedSudentsList);
    }

    @When("^getting students$")
    public void getting_students() throws Throwable {
        students = course.getStudents();
    }

    @Then("^students list is expected list$")
    public void students_list_is_expected_list() throws Throwable {
        assertThat(students, is(expectedSudentsList));
    }
}
