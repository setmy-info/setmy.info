package info.setmy.crawler.camel;

import info.setmy.crawler.dal.DataSourceConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

class CamelIT {

    private static final String BASE_WINDOWS_DIR = "C:\\pub\\setmy.info\\data\\crawler";
    private static final String BASE_UNIXES_DIR = "/TODO";

    CamelConfig camelConfig;
    Camel camel;
    ExampleService exampleService;
    String executorPoolId;
    CamelThreadsConfig camelThreadsConfig;
    String routeId;
    ScraperRouteBuilderConfig scraperRouteBuilderConfig;
    ScraperRouteBuilder routeBuilder;
    DataSourceConfig dataSourceConfig;
    String baseDirName;
    File baseDir;
    HomeDirectory homeDirectory;
    WorkingDirectory workingDirectory;

    @BeforeEach
    void setUp() {
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            baseDirName = BASE_WINDOWS_DIR;
        } else {
            baseDirName = BASE_UNIXES_DIR;
        }
        baseDir = new File(baseDirName);
        homeDirectory = new HomeDirectory(baseDir).init();
        workingDirectory = new WorkingDirectory(baseDir).init();
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
        scraperRouteBuilderConfig = ScraperRouteBuilderConfig.builder()
            .homeDirectory(homeDirectory)
            .workingDirectory(workingDirectory)
            .routeId(routeId)
            .executorPoolId(executorPoolId)
            .build();
        routeBuilder = new ScraperRouteBuilder(scraperRouteBuilderConfig);
        routeBuilder.addCamelBean(exampleService);
        dataSourceConfig = DataSourceConfig.builder()
            .jdbcUrl("jdbc:h2:./target/test-db;AUTO_SERVER=TRUE")
            .userName("sa")
            .password("")
            .maximumPoolSize(5)
            .build();
    }

    @Test
    void camelTest() {
        camel.bind(exampleService);
        camel.registerThreadPool(camelThreadsConfig);
        camel.add(routeBuilder);
        camel.run();
    }
}
