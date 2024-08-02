package info.setmy.templates;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import static freemarker.template.Configuration.VERSION_2_3_33;
import static freemarker.template.TemplateExceptionHandler.RETHROW_HANDLER;

public class PoCIT {

    private static final String TEMPLATE_DIR = "templates/";
    private static final String IN_FILE_NAME = "ExampleClass.java.ftl";
    private static final String OUT_FILE_NAME = "target/ExampleClass.java";

    private File outfile;

    @BeforeEach
    public void setup() {
        outfile = new File(OUT_FILE_NAME);
    }

    @Test
    public void poc() throws IOException, TemplateException {
        final Configuration configuration = new Configuration(VERSION_2_3_33);
        configuration.setDirectoryForTemplateLoading(new File("src/test/resources/templates/"));
        configuration.setClassForTemplateLoading(this.getClass(), "/");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        configuration.setWrapUncheckedExceptions(true);
        configuration.setFallbackOnNullLoopVariable(false);
        configuration.setSQLDateAndTimeTimeZone(TimeZone.getDefault());

        final PoCClass classData = PoCClass.builder()
            .className("ExampleClass")
            .templateName(TEMPLATE_DIR + IN_FILE_NAME)
            .build();
        classData.getAttributeNames().add("firstName");
        classData.getAttributeNames().add("lastName");

        final Template template = configuration.getTemplate(classData.getTemplateName());
        //final Template template = configuration.getTemplate(IN_FILE_NAME);

        final Writer out = new OutputStreamWriter(System.out);
        template.process(classData, out);

        try (FileWriter fileWriter = new FileWriter(outfile)) {
            template.process(classData, fileWriter);
        }
    }
}
