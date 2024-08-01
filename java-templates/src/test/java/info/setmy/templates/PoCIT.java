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

    private static final String TEMPLATE_DIR = "src/test/resources/templates/";
    private static final String IN_FILE_NAME = "ExampleClass.java.ftl";
    private static final String OUT_FILE_NAME = "target/ExampleClass.java";

    //private File inFile;
    private File outfile;

    @BeforeEach
    public void setup() {
        //inFile = new File(IN_FILE_NAME);
        outfile = new File(OUT_FILE_NAME);
    }

    @Test
    public void poc() throws IOException, TemplateException {
        final Configuration cfg = new Configuration(VERSION_2_3_33);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_DIR));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        cfg.setSQLDateAndTimeTimeZone(TimeZone.getDefault());

        final PoCClass classData = PoCClass.builder()
            .build();
        classData.getAttributeNames().add("firstName");
        classData.getAttributeNames().add("lastName");
        final Map<String, Object> root = new HashMap<>();
        root.put("className", "ExampleClass");
        root.put("classData", classData);

        final Template template = cfg.getTemplate("ExampleClass.java.ftl");

        final Writer out = new OutputStreamWriter(System.out);
        template.process(root, out);

        try (FileWriter fileWriter = new FileWriter(outfile)) {
            template.process(root, fileWriter);
        }
    }
}
