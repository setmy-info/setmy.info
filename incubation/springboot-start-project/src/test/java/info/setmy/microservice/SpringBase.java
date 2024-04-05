package info.setmy.microservice;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@SpringBootTest(classes = Application.class)
@DirtiesContext(classMode = AFTER_CLASS)
public class SpringBase {
}
