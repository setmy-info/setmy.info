package info.setmy.microservice.it;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

//@ActiveProfiles({"it"})
//@ActiveProfiles({"default"/*, "test"*/})
@ActiveProfiles({"default"/*, "it"*/})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class WebSpringBase {
}
