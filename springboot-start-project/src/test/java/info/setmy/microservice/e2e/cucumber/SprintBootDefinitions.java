package info.setmy.microservice.e2e.cucumber;

import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
public class SprintBootDefinitions {

    private static ConfigurableApplicationContext context;

    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @BeforeStep
    public void doSomethingBeforeStep(final Scenario scenario) {
    }

    @AfterStep
    public void doSomethingAfterStep(final Scenario scenario) {
    }

    @Given("spring boot environment is started")
    public void springBootIsStarted() {
        if (isSpringBootNotRunning()) {
            startSpringBoot();
        }
    }

    private boolean isSpringBootNotRunning() {
        return context == null;
    }

    private void startSpringBoot() {
        //context = SpringApplication.run(Application.class);
    }

    public void tearDown() {
        if (context != null) {
            context.close();
        }
    }
}
