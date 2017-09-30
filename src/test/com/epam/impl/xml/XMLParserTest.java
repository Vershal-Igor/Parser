package com.epam.impl.xml;

import com.epam.IParser;
import com.epam.ParserMaker;
import com.epam.ParserType;
import com.epam.entity.Article;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static com.epam.ParserMaker.getParserByName;
import static com.epam.impl.xml.XMLParser.inputStreamToString;
import static com.epam.impl.xml.XMLParser.returnArticleWithCorrectValues;
import static org.junit.Assert.*;

public class XMLParserTest {
    public static Logger logger = Logger.getLogger(XMLParserTest.class);

    private static final String DIRECTORY = "src/main/resources/files";
    private static final String TEST_DIRECTORY = "src/test/resources/files";

    private static final String XML_ARTICLE_2 = "src/main/resources/files/Article2.xml";
    private static final String TITLE_ARTICLE_2 = "The Java Platform module system";
    private static final String AUTHOR_ARTICLE_2 = "Sander Mak";

    private static final String XML_ARTICLE_3 = "src/main/resources/files/Article3.xml";
    private static final String TITLE_ARTICLE_3 = "Spring Framework - Overview";
    private static final String AUTHOR_ARTICLE_3 = "";

    private static final String XML_ARTICLE_5 = "src/main/resources/files/Article5.xml";
    private static final String TITLE_ARTICLE_5 = "";
    private static final String AUTHOR_ARTICLE_5 = "Thorben Janssen";

    private IParser XMLparser;
    private XmlMapper xmlMapper;

    @Before
    public void setUp() throws Exception {
        ParserMaker XMLmaker = getParserByName(ParserType.XML);
        XMLparser = XMLmaker.createParser();

        xmlMapper = new XmlMapper();
    }

    @Test
    public void parseXMLTest() throws Exception {
        assertEquals(XMLparser.getArticles(DIRECTORY), XMLparser.getArticles(TEST_DIRECTORY));
        logger.info(XMLparser.getArticles(DIRECTORY));
        System.out.println(XMLparser.getArticles(DIRECTORY));
    }


    @Test
    public void whenArticleGotFromXmlFile_thenCorrect() throws IOException {
        File xmlFile2 = new File(XML_ARTICLE_2);
        File xmlFile3 = new File(XML_ARTICLE_3);
        File xmlFile5 = new File(XML_ARTICLE_5);

        String xml2 = inputStreamToString(new FileInputStream(xmlFile2));
        String xml3 = inputStreamToString(new FileInputStream(xmlFile3));
        String xml5 = inputStreamToString(new FileInputStream(xmlFile5));

        Article article2 = xmlMapper.readValue(xml2, Article.class);
        Article article3 = xmlMapper.readValue(xml3, Article.class);
        Article article5 = xmlMapper.readValue(xml5, Article.class);

        logger.debug(returnArticleWithCorrectValues(article2));
        logger.debug(returnArticleWithCorrectValues(article3));
        logger.debug(returnArticleWithCorrectValues(article5));

        assertTrue(article2.getTitle().equals(TITLE_ARTICLE_2) &&
                article2.getAuthor().equals(AUTHOR_ARTICLE_2));
        assertTrue(article3.getTitle().equals(TITLE_ARTICLE_3) &&
                article3.getAuthor().equals(AUTHOR_ARTICLE_3));
        assertTrue(article5.getTitle().equals(TITLE_ARTICLE_5) &&
                article5.getAuthor().equals(AUTHOR_ARTICLE_5));
    }

}