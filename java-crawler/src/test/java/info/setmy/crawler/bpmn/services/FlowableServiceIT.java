package info.setmy.crawler.bpmn.services;

import info.setmy.crawler.bpmn.delegates.HelloWorldDelegate;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The embedded Flowable engine runs bpmn/hello-world.bpmn20.xml with its one service task.
 */
class FlowableServiceIT {

    static final String BPMN = "bpmn/hello-world.bpmn20.xml";
    static final String PROCESS_KEY = "helloWorld";

    FlowableService flowableService;
    ProcessEngine processEngine;

    @BeforeEach
    void setUp() {
        flowableService = new FlowableService();
        processEngine = flowableService.newInMemoryProcessEngine("crawler-it");
        flowableService.deploy(processEngine, BPMN);
    }

    @AfterEach
    void tearDown() {
        processEngine.close();
    }

    @Test
    void helloWorldServiceTaskRuns() {
        final ProcessInstance processInstance = flowableService.start(processEngine, PROCESS_KEY, Map.of());

        assertThat(processInstance.isEnded()).isTrue();
        assertThat(greeting(processInstance)).isEqualTo("Hello, World!");
    }

    @Test
    void serviceTaskReadsProcessVariable() {
        final ProcessInstance processInstance = flowableService.start(processEngine, PROCESS_KEY,
            Map.of(HelloWorldDelegate.NAME, "crawler"));

        assertThat(processInstance.isEnded()).isTrue();
        assertThat(greeting(processInstance)).isEqualTo("Hello, crawler!");
    }

    /**
     * The process has ended: its variables are in the history.
     */
    private String greeting(final ProcessInstance processInstance) {
        final HistoricVariableInstance variable = processEngine.getHistoryService()
            .createHistoricVariableInstanceQuery()
            .processInstanceId(processInstance.getId())
            .variableName(HelloWorldDelegate.GREETING)
            .singleResult();
        return (String) variable.getValue();
    }
}
