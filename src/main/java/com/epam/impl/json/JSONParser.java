package com.epam.impl.json;

import com.epam.deserializer.CustomDeserializer;
import com.epam.entity.Article;
import com.epam.exception.ParserException;
import com.epam.impl.AbstractParser;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.log4j.Logger;
//import org.eclipse.persistence.jaxb.UnmarshallerProperties;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONParser extends AbstractParser {
    private static Logger logger = Logger.getLogger(JSONParser.class);
    private static final String TYPE = "json";
    private static final String PARSER_EXCEPTION = "Exception while parsing";

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
            Article article = mapper.readValue(new File(directory), Article.class);

            articles.add(article);

        } catch (IOException e) {
            logger.error(PARSER_EXCEPTION, e);
            throw new ParserException(PARSER_EXCEPTION, e);
        }
        return articles;
    }

}

