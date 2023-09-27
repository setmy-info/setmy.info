package info.setmy.microservice;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringbootStartProjectApplication.class})
@ActiveProfiles(profiles = {"default", "test"})
@SpringBootTest
public abstract class SimpleSpringIntegrationTestBase {
}
