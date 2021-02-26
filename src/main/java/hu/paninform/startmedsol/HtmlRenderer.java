package hu.paninform.startmedsol;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

public class HtmlRenderer {

    public String getInputData() throws IOException {

        String template = this.getTemplate();
        IContext context = this.getData();

        ITemplateEngine engine = new TemplateEngine();
        String processedData = engine.process(template, context);
        System.out.println("Processed data: " + (processedData.length() < 1000 ? processedData : processedData.substring(0, 1000)));
        return processedData;

    }

    private IContext getData() throws IOException {
        Path path = Paths.get( App.SOURCES + "data.json");
        String data = Files.readAllLines(path).stream().collect(Collectors.joining(System.lineSeparator()));
        System.out.println("Data: " + (data.length() < 1000 ? data : data.substring(0, 1000)));

        Map<String, Object> variables = new Gson().fromJson(data, new TypeToken<HashMap<String, Object>>() {
        }.getType());
        return new Context(new Locale("hu", "HU"), variables);
    }

    private String getTemplate() throws IOException {
        Path path = Paths.get(App.SOURCES + "template.html");
        String template = Files.readAllLines(path).stream().collect(Collectors.joining(System.lineSeparator()));
        System.out.println("Template: " + (template.length() < 1000 ? template : template.substring(0, 1000)));
        return template;
    }

}
