package ee.pub.web;

import ee.pub.IntegrationTestBase;
import static net.sourceforge.jwebunit.junit.JWebUnit.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Imre Tabur <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Ignore
public class LoginIT extends IntegrationTestBase {

    @Before
    public void setup() {
        setBaseUrl("http://localhost:8080/tomcat-start-project");
    }

    @Test
    public void testLogin() {
        beginAt("/");
        assertTitleEquals("Login Page");
        setTextField("j_username", "login");
        setTextField("j_password", "g6p8");
        submit();
        assertTextPresent("Course Action");
    }
}
