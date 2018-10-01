/**
 * Copyright (c) Imre Tabur
 * All rights reserved.
 *
 * @author Imre Tabur
 */
package ee.pub.platform.lib;

import java.util.Locale;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LangLoaderTest {

    private LangLoader loader;
    private static final String BASE_NAME = "person";  // person.properties
    private static final String FIRST_NAME_LABEL = "person.first.name.label";
    private static final String LAST_NAME_LABEL = "person.last.name.label";

    public LangLoaderTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        loader = new LangLoader(BASE_NAME);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetStringDEE() {
        Locale loc = new Locale("et", "EE");
        String tmpFLabel = loader.getString(FIRST_NAME_LABEL, loc);
        String tmpLLabel = loader.getString(LAST_NAME_LABEL, loc);
        assertEquals("DefaultF_EE", tmpFLabel);
        assertEquals("DefaultL_EE", tmpLLabel);
    }

    @Test
    public void testGetStringDLV() {
        Locale loc = new Locale("lv", "LV");
        String tmpFLabel = loader.getString(FIRST_NAME_LABEL, loc);
        String tmpLLabel = loader.getString(LAST_NAME_LABEL, loc);
        assertEquals("DefaultF_LV", tmpFLabel);
        assertEquals("DefaultL_LV", tmpLLabel);
    }

    @Test
    public void testGetStringDLT() {
        Locale loc = new Locale("lt", "LT");
        String tmpFLabel = loader.getString(FIRST_NAME_LABEL, loc);
        String tmpLLabel = loader.getString(LAST_NAME_LABEL, loc);
        assertEquals("DefaultF_LT", tmpFLabel);
        assertEquals("DefaultL_LT", tmpLLabel);
    }

    @Test
    public void testGetStringDRU() {
        Locale loc = new Locale("ru", "RU");
        String tmpFLabel = loader.getString(FIRST_NAME_LABEL, loc);
        String tmpLLabel = loader.getString(LAST_NAME_LABEL, loc);
        assertEquals("DefaultF_RU", tmpFLabel);
        assertEquals("DefaultL_RU", tmpLLabel);
    }
}
