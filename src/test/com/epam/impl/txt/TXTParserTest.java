package com.epam.impl.txt;

import com.epam.IParser;
import com.epam.ParserMaker;
import com.epam.ParserType;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static com.epam.ParserMaker.getParserByName;
import static org.junit.Assert.*;

public class TXTParserTest {


    private static Logger logger = Logger.getLogger(TXTParserTest.class);
    private static final String DIRECTORY = "src/main/resources/files";
    private static final String TEST_DIRECTORY = "src/test/resources/files";

    private static final String XML_ARTICLE_7 = "src/main/resources/files/Article7.txt";
    private static final String AUTHOR_ARTICLE_7 = "Jonathan Hult";

    private static final String XML_ARTICLE_8 = "src/main/resources/files/Article8.txt";
    private static final String AUTHOR_ARTICLE_8 = "Ricky Ho";

    private static final String XML_ARTICLE_9 = "src/test/resources/files/TestArticle9.txt";
    private static final String AUTHOR_ARTICLE_9 = "UNKNOWN";
    private IParser TXTparser;
    private TXTParser txtParser;

    @Before
    public void setUp() throws Exception {
        ParserMaker TXTmaker = getParserByName(ParserType.TXT);
        TXTparser = TXTmaker.createParser();
        txtParser = new TXTParser();
    }

    @Test
    public void parseTXTTest() throws Exception {
        assertEquals(TXTparser.getArticles(DIRECTORY), TXTparser.getArticles(TEST_DIRECTORY));
        logger.info(TXTparser.getArticles(DIRECTORY));
    }

    @Test
    public void returnAuthorNameTest() throws Exception {
        assertTrue(txtParser.pullAuthorName(XML_ARTICLE_7).equals(AUTHOR_ARTICLE_7));
        logger.info(txtParser.pullAuthorName(XML_ARTICLE_7));
        assertTrue(txtParser.pullAuthorName(XML_ARTICLE_8).equals(AUTHOR_ARTICLE_8));
        logger.info(txtParser.pullAuthorName(XML_ARTICLE_8));
        assertTrue(txtParser.pullAuthorName(XML_ARTICLE_9).equals(AUTHOR_ARTICLE_9));
        logger.info(txtParser.pullAuthorName(XML_ARTICLE_9));
    }


}