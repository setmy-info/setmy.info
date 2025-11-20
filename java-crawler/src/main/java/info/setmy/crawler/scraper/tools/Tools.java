package info.setmy.crawler.scraper.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.setmy.crawler.scraper.ScrapedContent;
import info.setmy.crawler.scraper.Scraper;
import info.setmy.crawler.camel.Named;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.ThreadPoolProfile;

import java.io.File;
import java.io.IOException;

public record Tools(
    Scraper scraper,
    ObjectMapper objectMapper,
    CamelContext context
) {

    public static final String SCRAPER_POOL = "scraperPool";
/*
    public static Tools newTools(final ScraperConfig scraperConfig, final SeleniumExtended seleniumExtended) {
        final Scraper scraper = new Scraper(scraperConfig, seleniumExtended);
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
    */

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
