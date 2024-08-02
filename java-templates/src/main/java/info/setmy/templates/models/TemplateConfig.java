package info.setmy.templates.models;


import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.TimeZone;

import static freemarker.template.Configuration.VERSION_2_3_33;
import static freemarker.template.TemplateExceptionHandler.RETHROW_HANDLER;

@Getter
@RequiredArgsConstructor
public class TemplateConfig {

    private final File templatesDirectory;

    private Configuration config;

    public TemplateConfig(final String templatesDirectory) {
        this.templatesDirectory = new File(templatesDirectory);
    }

    public Configuration getConfiguration() {
        if (config != null) {
            return config;
        }
        config = new Configuration(VERSION_2_3_33);
        if (templatesDirectory != null) {
            try {
                config.setDirectoryForTemplateLoading(templatesDirectory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        config.setClassForTemplateLoading(this.getClass(), "/");
        config.setDefaultEncoding("UTF-8");
        config.setTemplateExceptionHandler(RETHROW_HANDLER);
        config.setLogTemplateExceptions(false);
        config.setWrapUncheckedExceptions(true);
        config.setFallbackOnNullLoopVariable(false);
        config.setSQLDateAndTimeTimeZone(TimeZone.getDefault());
        return config;
    }

    public Template getTemplate(final String name) {
        try {
            return getConfiguration().getTemplate(name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
