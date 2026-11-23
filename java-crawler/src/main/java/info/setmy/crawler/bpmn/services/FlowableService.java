package info.setmy.crawler.bpmn.services;

import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;

import java.util.Map;

/**
 * Embedded Flowable BPMN engine: the engine runs inside the crawler JVM, no server.
 */
@Log4j2
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class FlowableService {

    /**
     * Engine with its own in-memory H2 DB named by the engine: Flowable creates its tables at start. The DB is gone
     * when the engine is closed (its last connection), therefore no schema drop (the Flowable drop script fails on
     * H2 2.x). The caller closes the engine.
     */
    public ProcessEngine newInMemoryProcessEngine(final String engineName) {
        final ProcessEngine processEngine = ProcessEngineConfiguration
            .createStandaloneInMemProcessEngineConfiguration()
            .setEngineName(engineName)
            .setJdbcUrl("jdbc:h2:mem:" + engineName)
            .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
            // Service tasks run synchronously in the calling thread, no job executor threads
            .setAsyncExecutorActivate(false)
            .buildProcessEngine();
        log.info("Flowable engine {} started", processEngine.getName());
        return processEngine;
    }

    /**
     * @param bpmnResource class path resource, the name must end with .bpmn20.xml or .bpmn
     */
    public Deployment deploy(final ProcessEngine processEngine, final String bpmnResource) {
        final Deployment deployment = processEngine.getRepositoryService()
            .createDeployment()
            .addClasspathResource(bpmnResource)
            .deploy();
        log.info("Deployed {}: deployment {}", bpmnResource, deployment.getId());
        return deployment;
    }

    public ProcessInstance start(final ProcessEngine processEngine, final String processKey,
                                 final Map<String, Object> variables) {
        final ProcessInstance processInstance = processEngine.getRuntimeService()
            .startProcessInstanceByKey(processKey, variables);
        log.info("Process {} instance {} started, ended: {}", processKey, processInstance.getId(),
            processInstance.isEnded());
        return processInstance;
    }
}
