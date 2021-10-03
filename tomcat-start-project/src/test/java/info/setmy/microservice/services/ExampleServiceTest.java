package info.setmy.microservice.services;

import info.setmy.microservice.dao.ExampleDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@ExtendWith(MockitoExtension.class)
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
