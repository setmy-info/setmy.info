package info.setmy.microservice.it;

import info.setmy.microservice.Application;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = Application.class)
@ActiveProfiles({"it"})
public class SpringBase {
}
