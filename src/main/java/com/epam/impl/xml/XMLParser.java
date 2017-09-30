package com.epam.impl.xml;


import com.epam.entity.Article;
import com.epam.exception.ParserException;
import com.epam.impl.AbstractParser;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class XMLParser extends AbstractParser {
    public static Logger logger = Logger.getLogger(XMLParser.class);
    private static final String TYPE = "xml";
    private static final String NO_ELEMENT = "";
    private static final String PARSER_EXCEPTION = "Exception while parsing";

    public XMLParser() {
        super(TYPE);
    }

    public List<Article> parse(String directory) throws ParserException {
        List<Article> articles = new ArrayList<>();
        File xmlFile = new File(directory);
        XmlMapper xmlMapper = new XmlMapper();
        String xml = null;
        try {
            xml = inputStreamToString(new FileInputStream(xmlFile));
            Article value = xmlMapper.readValue(xml, Article.class);
            articles.add(returnArticleWithCorrectValues(value));
        } catch (IOException e) {
            logger.error(PARSER_EXCEPTION, e);
            throw new ParserException(PARSER_EXCEPTION, e);
        }
        return articles;
    }

    static Article returnArticleWithCorrectValues(Article value) {
        if (value.getTitle() == null) {
            value.setTitle(NO_ELEMENT);
        }
        if (value.getAuthor() == null) {
            value.setAuthor(NO_ELEMENT);
        }
        return value;
    }

    static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line.trim());
        }
        br.close();
        return sb.toString();
    }

    /*public List<Article> parse(String directory) throws ParserException {
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
    }*/

}
