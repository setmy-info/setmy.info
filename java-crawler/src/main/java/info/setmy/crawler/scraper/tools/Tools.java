package info.setmy.crawler.scraper.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.setmy.crawler.scraper.ScrapedContent;
import info.setmy.crawler.scraper.Scraper;
import info.setmy.crawler.scraper.ScraperConfig;
import info.setmy.crawler.scraper.tools.camel.beans.ExampleService;
import info.setmy.crawler.scraper.tools.camel.beans.Named;
import info.setmy.crawler.scraper.tools.camel.beans.ScraperRouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.ThreadPoolProfile;

import java.io.File;
import java.io.IOException;

import static info.setmy.crawler.scraper.Scraper.newScraper;

public record Tools(
    Scraper scraper,
    ObjectMapper objectMapper,
    CamelContext context
) {

    public static final String SCRAPER_POOL = "scraperPool";

    public static Tools newTools(final ScraperConfig scraperConfig) {
        final Scraper scraper = newScraper(scraperConfig);
        return new Tools(scraper, new ObjectMapper(), new DefaultCamelContext());
    }

    public static Tools newTools(final ScrapingCommand scrapingCommand) {
        var tool = newTools(scrapingCommand.getScraperConfig());
        ThreadPoolProfile threadPoolProfile = toThreadPoolProfile(scrapingCommand);
        tool.bind(new ExampleService());
        tool.context.getExecutorServiceManager().registerThreadPoolProfile(threadPoolProfile);
        tool.add(new ScraperRouteBuilder());
        return tool;
    }

    private void bind(Named named) {
        context.getRegistry().bind(named.getName(), named);
    }

    private void add(RouteBuilder routeBuilder) {
        try {
            context.addRoutes(routeBuilder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void execute() {
        context.start();
        ProducerTemplate template = context.createProducerTemplate();
        for (int i = 0; i < 10; i++) {
            template.asyncSendBody("direct:start", "Begin " + i);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        context.stop();
    }

    private static ThreadPoolProfile toThreadPoolProfile(ScrapingCommand scrapingCommand) {
        ThreadPoolProfile threadPoolProfile = new ThreadPoolProfile();
        threadPoolProfile.setId(SCRAPER_POOL);
        threadPoolProfile.setPoolSize(scrapingCommand.getPoolSize());
        threadPoolProfile.setMaxPoolSize(scrapingCommand.getMaxPoolSize());
        threadPoolProfile.setMaxQueueSize(scrapingCommand.getMaxQueueSize());
        return threadPoolProfile;
    }

    public void parse(final String url, final File file) {
        final ScrapedContent scrapedContent = scraper.parse(url);
        try {
            objectMapper.writeValue(file, scrapedContent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String parse(final String url) {
        final ScrapedContent scrapedContent = scraper.parse(url);
        try {
            return objectMapper.writeValueAsString(scrapedContent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
