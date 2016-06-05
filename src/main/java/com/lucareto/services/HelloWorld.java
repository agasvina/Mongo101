package com.lucareto.services;

import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.port;
import static spark.Spark.post;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lucareto.db.PersonDAO;

import freemarker.template.Configuration;
import freemarker.template.Template;


public class HelloWorld {
    private static PersonDAO pDao = new PersonDAO();
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    private static Map<String, Object> helloMap = new HashMap<>();

    public static void main(String[] args) {
        port(8080);
        helloMap.put("answer", 42);
        
        get("/", (req, res) ->{
            return renderHtml(helloMap);
        });
        
        get("/:id", (req, res) -> {
            String id = req.params(":id").toString();
            return gson.toJson(pDao.getById(id));
        });
        
        post("/changeValue", (req, res) -> {
            helloMap.put("answer", req.queryParams("answer"));
            res.redirect("/");
            return null;
        });
        
        Dummy.test();
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