package info.setmy.crawler.camel;

import info.setmy.crawler.camel.services.CSVService;
import info.setmy.crawler.dal.DataSourceConfig;
import info.setmy.crawler.dal.DataSourceFactory;
import info.setmy.crawler.dal.HibernateComponent;
import info.setmy.crawler.dal.LiquibaseComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

class CamelIT {

    private static final String BASE_WINDOWS_DIR = "C:\\pub\\setmy.info\\data\\crawler";
    private static final String BASE_UNIXES_DIR = "/TODO";

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
    DataSource dataSource;
    LiquibaseComponent liquibaseComponent;
    Map<String, Object> hibernateProperties;
    HibernateComponent hibernateComponent;
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
        dataSource = DataSourceFactory.getInstance().newDataSource(dataSourceConfig);
        liquibaseComponent = new LiquibaseComponent(dataSource, "db/changelog/db.changelog-master.xml")
            .migrate();
        hibernateProperties = new HashMap<>();
        hibernateProperties.put("hibernate.connection.datasource", dataSource);
        hibernateProperties.put("hibernate.hikari.dataSource", dataSource);
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProperties.put("hibernate.hbm2ddl.auto", "validate"); // validate, update, create, create-drop
        hibernateProperties.put("hibernate.show_sql", "true");
        hibernateProperties.put("hibernate.format_sql", "true");
        hibernateProperties.put("hibernate.use_sql_comments", "true");
        hibernateProperties.put("hibernate.jdbc.batch_size", "20");
        hibernateProperties.put("hibernate.order_inserts", "true");
        hibernateProperties.put("hibernate.order_updates", "true");
        hibernateProperties.put("hibernate.batch_fetch_style", "DYNAMIC");
        hibernateProperties.put("hibernate.hikari.minimumIdle", "2");
        hibernateProperties.put("hibernate.hikari.maximumPoolSize", "10");
        hibernateProperties.put("hibernate.hikari.idleTimeout", "30000");
        hibernateProperties.put("hibernate.hikari.connectionTimeout", "20000");
        hibernateProperties.put("hibernate.hikari.maxLifetime", "120000");
        hibernateProperties.put("jakarta.persistence.nonJtaDataSource", dataSource);

        hibernateComponent = new HibernateComponent(hibernateProperties, dataSource)
            .init();
    }

    @Test
    void camelTest() {
        camel.bind(exampleService);
        camel.bind(csvService);
        camel.registerThreadPool(camelThreadsConfig);
        camel.add(routeBuilder);
        camel.run();
    }
}
