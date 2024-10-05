package info.setmy.microservice.it;

import info.setmy.microservice.Application;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
//@ActiveProfiles({"default"/*, "it"*/})
public class SpringBase {
}
