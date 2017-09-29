package com.epam.impl.json;

import com.epam.deserializer.CustomDeserializer;
import com.epam.entity.Article;
import com.epam.exception.ParserException;
import com.epam.impl.AbstractParser;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
//import org.eclipse.persistence.jaxb.UnmarshallerProperties;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONParser extends AbstractParser {
    private static final String TYPE = "json";

    public JSONParser() {
        super(TYPE);
    }

    @Override
    public List<Article> parse(String directory) throws ParserException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        List<Article> articles = new ArrayList<>();
        try {
            module.addDeserializer(Article.class, new CustomDeserializer());
            mapper.registerModule(module);
           // mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
            Article article = mapper.readValue(new File(directory), Article.class);

            //Pretty print
            /*String prettyStaff = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(article);
            System.out.println(prettyStaff);*/

            articles.add(article);


        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articles;
    }

}

