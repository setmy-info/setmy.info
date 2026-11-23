package info.setmy.crawler.bpmn.delegates;

import lombok.extern.log4j.Log4j2;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * Service task of bpmn/hello-world.bpmn20.xml: logs a greeting and stores it as the process variable "greeting".
 */
@Log4j2
public class HelloWorldDelegate implements JavaDelegate {

    public static final String NAME = "name";
    public static final String GREETING = "greeting";

    @Override
    public void execute(final DelegateExecution execution) {
        final Object name = execution.getVariable(NAME);
        final String greeting = "Hello, " + (name != null ? name : "World") + "!";
        log.info("{} (process instance {}, activity {})", greeting, execution.getProcessInstanceId(),
                execution.getCurrentActivityId());
        execution.setVariable(GREETING, greeting);
    }
}
