package info.setmy.microservice.it;

import info.setmy.microservice.Application;
import jakarta.transaction.Transactional;
import org.springframework.boot.test.context.SpringBootTest;

@Transactional
@SpringBootTest//(classes = Application.class)
//@ActiveProfiles({"default"/*, "it"*/})
public abstract class SpringBase {
}
