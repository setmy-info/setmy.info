/**
 * Copyright (c) Imre Tabur
 * All rights reserved.
 *
 * @author Imre Tabur
 */
package ee.pub.platform.lib.stateless;

import org.junit.Ignore;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SysContextBeanTest {

    private InitialContext initialContext;

    public SysContextBeanTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        try {
            initialContext = new InitialContext();
        } catch (NamingException ex) {
            fail("Context creation error: " + ex.toString());
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    @Ignore
    public void testLocal() throws Exception {
        // TODO
    }
}
