package info.setmy.templates;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import info.setmy.templates.models.TemplateConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

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
        final TemplateConfig templateConfig = new TemplateConfig("src/test/resources/templates/");
        final PoCClass classData = PoCClass.builder()
            .templateConfig(templateConfig)
            .className("ExampleClass")
            .templateName(TEMPLATE_DIR + IN_FILE_NAME)
            .build();
        classData.getAttributeNames().add("firstName");
        classData.getAttributeNames().add("lastName");
        classData.getAttributes().add(
            PoCAttribute.builder()
                .templateConfig(templateConfig)
                .name("firstName")
                .templateName("attribute.ftl")
                .build()
        );

        final Template template = templateConfig.getTemplate(classData.getTemplateName());

        final Writer out = new OutputStreamWriter(System.out);
        template.process(classData, out);

        try (FileWriter fileWriter = new FileWriter(outfile)) {
            template.process(classData, fileWriter);
        }
    }
}
