package info.setmy.microservice.services;

import info.setmy.microservice.dao.ExampleDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class ExampleServiceTest {

    @Spy
    @InjectMocks
    ExampleService exampleService;

    @Mock
    ExampleDao exampleDao;

    @Test
    public void testGetExampleDao() {
        //exampleService.getExampleModel();
    }
}
