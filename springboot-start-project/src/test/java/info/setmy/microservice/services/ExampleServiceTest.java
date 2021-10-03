package info.setmy.microservice.services;

import info.setmy.microservice.dao.ExampleDao;
import info.setmy.microservice.dao.ExampleRepository;
import info.setmy.microservice.dao.JDBCExampleDao;
import info.setmy.microservice.dao.JPAExampleDao;
import info.setmy.microservice.dao.PersonRepository;
import info.setmy.microservice.properties.ExampleProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ExampleServiceTest {

    @Spy
    @InjectMocks
    ExampleService exampleService;

    @Mock
    ExampleDao exampleDao;

    @Mock
    ExampleProperties exampleProperties;

    @Mock
    ExampleRepository exampleRepository;

    @Mock
    JDBCExampleDao jdbcExampleDao;

    @Mock
    JPAExampleDao jpaExampleDao;

    @Mock
    PersonRepository personRepository;

    @Mock
    Cache cache;

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetExampleDao() {
        //exampleService.getExampleModel();
    }
}
