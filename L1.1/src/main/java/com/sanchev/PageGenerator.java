package com.sanchev;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class PageGenerator {

    private static final String HTML_DIR = "src/templates";

    private static PageGenerator instance;
    private final Configuration configuration;

    private PageGenerator() {
        configuration = new Configuration();
    }

    public static PageGenerator getInstance() {
        if (instance == null)
            instance = new PageGenerator();
        return instance;
    }

    public String getPage(String fileName, Map<String, Object> data) {
        Writer writer = new StringWriter();
        try {
            Template template = configuration.getTemplate(HTML_DIR + File.separator + fileName);
            template.process(data, writer);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }

        return writer.toString();
    }

}