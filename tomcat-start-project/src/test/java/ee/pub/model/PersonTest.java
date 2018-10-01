package ee.pub.model;

import ee.pub.TestBase;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Person class test.
 *
 * mvn -Dtest=PersonTest test
 *
 * @author Imre Tabur <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class PersonTest extends TestBase {

    private Person instance;

    public PersonTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        this.instance = new Person();
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
        assertNull(this.instance.getFirstName());
        assertNull(this.instance.getLastName());
    }

    @Test
    public void testAgeSetGetTest() {
        int age = 99;
        this.instance.setAge(age);
        assertEquals(age, this.instance.getAge());
    }

    @Test
    public void testFirstNameSetGetTest() {
        String name = "James";
        this.instance.setFirstName(name);
        assertEquals(name, this.instance.getFirstName());
    }

    @Test
    public void testLastNameSetGetTest() {
        String name = "Bond";
        this.instance.setLastName(name);
        assertEquals(name, this.instance.getLastName());
    }

    @Test
    public void testIdSetGetTest() {
        Long num = 123L;
        this.instance.setId(123L);
        assertEquals(num, this.instance.getId());
    }
}
