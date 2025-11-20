package info.setmy.crawler.camel;

import org.apache.camel.builder.RouteBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CamelIT {

    CamelConfig camelConfig;
    Camel camel;
    Named exampleService;
    String executorPoolId;
    CamelThreadsConfig camelThreadsConfig;
    String routeId;
    RouteBuilder routeBuilder;

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
    }

    @Test
    void camelTest() {
        camel.bind(exampleService);
        camel.registerThreadPool(camelThreadsConfig);
        camel.add(routeBuilder);
        camel.run();
    }
}
