package ee.pub;

import ee.pub.web.actions.CourseAction;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@Ignore
@ContextConfiguration(locations = {"file:src/test/resources/service.test.xml"})
public class IntegrationTestBase {

    @Autowired
    CourseAction courseAction;
}
