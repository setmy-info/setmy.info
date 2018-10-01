/**
 * Copyright (c) Imre Tabur
 * All rights reserved.
 *
 * @author Imre Tabur
 */
package ee.pub.platform.lib;

import ee.pub.platform.lib.ui.PersonUI;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonTest {

    public static final String FIRST_NAME = "Fish";
    public static final String LAST_NAME = "Finger";

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
    }

    @After
    public void tearDown() {
    }

    private Person preparePersonData() {
        Person person = new Person();
        person.setFirstName(FIRST_NAME);
        person.setLastName(LAST_NAME);
        return person;
    }

    @Test
    public void testPerson() {
        Person person = this.preparePersonData();
        assertEquals(FIRST_NAME, person.getFirstName());
        assertEquals(LAST_NAME, person.getLastName());
    }

    @Test
    public void testPersonUI() {
        PersonUI personUI = new PersonUI();
        personUI.setPerson(this.preparePersonData());
        assertEquals(FIRST_NAME, personUI.getPerson().getFirstName());
        assertEquals(LAST_NAME, personUI.getPerson().getLastName());
    }
}
