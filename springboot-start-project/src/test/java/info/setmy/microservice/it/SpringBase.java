package info.setmy.microservice.it;

import info.setmy.microservice.Application;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@SpringBootTest(classes = Application.class)
@DirtiesContext(classMode = AFTER_CLASS)
@ActiveProfiles({"default"/*, "it"*/})
public class SpringBase {
}
