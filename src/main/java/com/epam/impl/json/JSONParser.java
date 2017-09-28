package com.epam.impl.json;

import com.epam.entity.Article;
import com.epam.exception.ParserException;
import com.epam.impl.AbstractParser;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JSONParser extends AbstractParser {
    private static final String TYPE = "json";

    public JSONParser() {
        super(TYPE);
    }

    @Override
    public List<Article> parse(String directory) throws ParserException {
        List<Article> articles = new ArrayList<>();
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
        return articles;
    }
}
