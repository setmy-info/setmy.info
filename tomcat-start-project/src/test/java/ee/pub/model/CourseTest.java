package ee.pub.model;

import ee.pub.TestBase;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Courses class test.
 *
 * mvn -Dtest=CourseTest test
 *
 * @author Imre Tabur <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class CourseTest extends TestBase {

    private Course instance;

    public CourseTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        this.instance = new Course();
    }

    @After
    public void tearDown() {
    }

    /**
     * Instance null test.
     */
    @Test
    public void instanceTest() {
        assertNotNull(this.instance);
    }

    /**
     * state test.
     */
    @Test
    public void testStudentsTest() {
        assertNull(this.instance.getId());
        assertNull(this.instance.getName());
        assertNull(this.instance.getStudents());
    }

    @Test
    public void testStudentsSetGetTest() {
        List<Person> result = new ArrayList<Person>();
        this.instance.setStudents(result);
        assertSame(result, this.instance.getStudents());
    }

    @Test
    public void testNameSetGetTest() {
        String name = "Java Programming Course";
        this.instance.setName(name);
        assertEquals(name, this.instance.getName());
    }

    @Test
    public void testIdSetGetTest() {
        Long num = 123L;
        this.instance.setId(123L);
        assertEquals(num, this.instance.getId());
    }
}
