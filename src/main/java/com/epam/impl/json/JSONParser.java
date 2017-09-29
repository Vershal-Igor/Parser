package com.epam.impl.json;

import com.epam.entity.Article;
import com.epam.exception.ParserException;
import com.epam.impl.AbstractParser;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        List<Article> articles = new ArrayList<>();
        try {
            mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
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
       /* List<Article> articles = new ArrayList<>();
        try {
            System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
            JAXBContext context = JAXBContext.newInstance(Article.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");

            Object o = unmarshaller.unmarshal(new File(directory));
            articles.add((Article) o);


        } catch (JAXBException e) {
            throw new ParserException("Parsing exception", e);
        }
        return articles;*/
}

