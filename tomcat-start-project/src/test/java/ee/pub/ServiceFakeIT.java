package ee.pub;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceFakeIT extends IntegrationTestBase {

    private String name = "Name";

    @Test
    public void serviceFakeTest() {
        assertNotNull(this.courseAction.getCourseService().findAllCourses());
    }

    @Test
    public void serviceFakePersonNameExist() {
        //assertNotNull(this.courseAction.getService().find(name).getName());
    }

    @Test
    public void serviceFakePersonTest() {
        //assertNotNull(this.courseAction.getService().find(name).getName().equals(name));
    }
}
