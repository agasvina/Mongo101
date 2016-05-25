package com.mongodb.services;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.halt;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorld {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    private static Map<String, Object> helloMap = new HashMap<>();

    public static void main(String[] args) {
        helloMap.put("answer", 42);
        
        get("/", (req, res) ->{
            return renderHtml(helloMap);
        });
        
        post("/changeValue", (req, res) -> {
            helloMap.put("answer", req.queryParams("answer"));
            res.redirect("/");
            return null;
        });
    }

    public static StringWriter renderHtml(Map<String, Object> value) {
        StringWriter writer = new StringWriter();
        try {
            Configuration configuration = new Configuration();
            configuration.setClassForTemplateLoading(HelloWorld.class, "/");
            Template helloTemplate = configuration.getTemplate("client.ftl");
            helloTemplate.process(value, writer);
        } catch (Exception e) {
            halt(500);
            logger.warn("Unable to render the freemarker template", e);
        }
        return writer;
    }
}