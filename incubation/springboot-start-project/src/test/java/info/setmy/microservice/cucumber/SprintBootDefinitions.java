package info.setmy.microservice.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import org.springframework.context.ConfigurableApplicationContext;

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
