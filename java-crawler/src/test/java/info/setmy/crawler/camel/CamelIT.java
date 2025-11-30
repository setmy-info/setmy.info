package info.setmy.crawler.camel;

import info.setmy.crawler.camel.services.CSVService;
import info.setmy.crawler.camel.services.ExampleService;
import info.setmy.crawler.dal.DataSourceConfig;
import info.setmy.crawler.elt.models.HomeDirectory;
import info.setmy.crawler.elt.models.WorkingDirectory;
import info.setmy.crawler.elt.services.DataSourceFactoryService;
import info.setmy.crawler.elt.services.GlobalConfigService;
import info.setmy.crawler.elt.services.GuiceService;
import info.setmy.crawler.elt.services.HibernateService;
import info.setmy.crawler.elt.services.TransformsService;
import info.setmy.crawler.scraper.camel.ScraperRouteBuilder;
import info.setmy.crawler.scraper.camel.ScraperRouteBuilderConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;

public class CamelIT {

    public static final String BASE_WINDOWS_DIR = "C:\\pub\\setmy.info\\data\\crawler";
    public static final String BASE_UNIXES_DIR = "/TODO";

    CamelConfig camelConfig;
    Camel camel;
    ExampleService exampleService;
    CSVService csvService;
    String executorPoolId;
    CamelThreadsConfig camelThreadsConfig;
    String routeId;
    ScraperRouteBuilderConfig scraperRouteBuilderConfig;
    ScraperRouteBuilder routeBuilder;
    DataSourceConfig dataSourceConfig;
    HibernateService hibernateService;
    String homeDirectoryNameString;
    String workingDirectoryNameString;
    File homeDirectoryFile;
    File workingDirectoryFile;
    HomeDirectory homeDirectory;
    WorkingDirectory workingDirectory;
    GuiceService guiceService;
    GlobalConfigService globalConfigService;
    TransformsService transformsService;
    DataSourceFactoryService dataSourceFactoryService;

    @BeforeEach
    void setUp() {
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            homeDirectoryNameString = BASE_WINDOWS_DIR;
            workingDirectoryNameString = BASE_WINDOWS_DIR;
        } else {
            homeDirectoryNameString = BASE_UNIXES_DIR;
            workingDirectoryNameString = BASE_UNIXES_DIR;
        }
        homeDirectoryFile = new File(homeDirectoryNameString);
        workingDirectoryFile = new File(workingDirectoryNameString);
        guiceService = new GuiceService(homeDirectoryFile, workingDirectoryFile)
            .init();
        homeDirectory = guiceService.getInjector().getInstance(HomeDirectory.class);
        workingDirectory = guiceService.getInjector().getInstance(WorkingDirectory.class);
        globalConfigService = guiceService.getInjector().getInstance(GlobalConfigService.class);
        transformsService = guiceService.getInjector().getInstance(TransformsService.class);
        dataSourceFactoryService = guiceService.getInjector().getInstance(DataSourceFactoryService.class);
        hibernateService = guiceService.getInjector().getInstance(HibernateService.class);

        camelConfig = CamelConfig.builder()
            .build();
        camel = new Camel(camelConfig);
        exampleService = new ExampleService();
        csvService = new CSVService();
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
        dataSourceConfig = DataSourceConfig.builder()
            .jdbcUrl("jdbc:h2:" + workingDirectory.getOutput().getAbsolutePath() + "/test-db")
            .userName("sa")
            .password("")
            .maximumPoolSize(5)
            .build();
    }

    @Test
    @Disabled
    void camelTest() {
        camel.bind(exampleService);
        camel.bind(csvService);
        camel.registerThreadPool(camelThreadsConfig);
        camel.add(routeBuilder);
        camel.run();
    }
}
