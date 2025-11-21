package info.setmy.crawler.camel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CamelIT {

    CamelConfig camelConfig;
    Camel camel;
    ExampleService exampleService;
    String executorPoolId;
    CamelThreadsConfig camelThreadsConfig;
    String routeId;
    ScraperRouteBuilder routeBuilder;

    @BeforeEach
    void setUp() {
        camelConfig = CamelConfig.builder()
            .build();
        camel = new Camel(camelConfig);
        exampleService = new ExampleService();
        executorPoolId = "testPool";
        camelThreadsConfig = CamelThreadsConfig.builder()
            .poolId("testPool")
            .maxQueueSize(5)
            .poolSize(5)
            .maxQueueSize(0)
            .build();
        routeId = "testRoute";
        routeBuilder = new ScraperRouteBuilder(routeId, executorPoolId);
        routeBuilder.addCamelBean(exampleService);
    }

    @Test
    void camelTest() {
        camel.bind(exampleService);
        camel.registerThreadPool(camelThreadsConfig);
        camel.add(routeBuilder);
        camel.run();
    }
}
