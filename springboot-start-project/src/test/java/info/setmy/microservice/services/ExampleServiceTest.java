package info.setmy.microservice.services;

import info.setmy.microservice.dao.ExampleDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleServiceTest {

    @Spy
    @InjectMocks
    ExampleService exampleService;

    @Mock
    ExampleDao exampleDao;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetExampleDao() {
        //exampleService.getExampleModel();
    }
}
