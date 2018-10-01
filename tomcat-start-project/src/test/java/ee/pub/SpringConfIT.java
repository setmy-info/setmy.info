package ee.pub;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class SpringConfIT extends IntegrationTestBase {

    @Test
    public void userActionExistingTest() {
        assertNotNull(courseAction);
    }

    @Test
    public void userActionServiceExistingTest() {
        assertNotNull(courseAction.getCourseService());
    }
}
