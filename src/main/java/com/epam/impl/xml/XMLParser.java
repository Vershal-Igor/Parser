package com.epam.impl.xml;

import com.epam.IParser;
import com.epam.entity.Article;
import com.epam.exception.ParserException;
import com.epam.impl.AbstractParser;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class XMLParser extends AbstractParser {
    private static final String TYPE = "xml";

    public XMLParser() {
        super(TYPE);
    }

    public List<Article> parse(String directory) throws ParserException {
        List<Article> articles = new ArrayList<>();
        try {
            JAXBContext context = JAXBContext.newInstance(Article.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Object o = unmarshaller.unmarshal(new File(directory));
            articles.add((Article) o);
        } catch (JAXBException e) {
            throw new ParserException("Parsing exception", e);
        }
        return articles;
    }

}
