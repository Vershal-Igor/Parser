package com.epam.impl.txt;

import com.epam.IParser;
import com.epam.ParserMaker;
import com.epam.ParserType;
import com.epam.exception.ParserException;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.epam.ParserMaker.getParserByName;
import static org.junit.Assert.*;

public class TXTParserTest {
    private static Logger logger = Logger.getLogger(TXTParserTest.class);

    private static final String TYPE = "txt";

    private static final String DIRECTORY = "src/main/resources/files";
    private static final String TEST_DIRECTORY = "src/test/resources/files";
    private static final String FAIL_DIRECTORY = "src/main/resources/file";

    private static final String TXT_ARTICLE_7 = "src/main/resources/files/Article7.txt";
    private static final String AUTHOR_ARTICLE_7 = "Jonathan Hult";

    private static final String TXT_ARTICLE_8 = "src/main/resources/files/Article8.txt";
    private static final String AUTHOR_ARTICLE_8 = "Ricky Ho";

    private static final String TXT_ARTICLE_9 = "src/test/resources/files/TestArticle9.txt";
    private static final String AUTHOR_ARTICLE_9 = "UNKNOWN";

    private static final String[] TXT_FILES = {"src\\main\\resources\\files\\Article7.txt",
            "src\\main\\resources\\files\\Article8.txt", "src\\main\\resources\\files\\TestArticle9.txt"};

    private IParser TXTparser;
    private TXTParser txtParser;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        ParserMaker TXTmaker = getParserByName(ParserType.TXT);
        TXTparser = TXTmaker.createParser();
        txtParser = new TXTParser();
    }

    @Test
    public void shouldParseTXT() throws Exception {
        assertEquals(TXTparser.getArticles(DIRECTORY), TXTparser.getArticles(TEST_DIRECTORY));
        logger.info(TXTparser.getArticles(DIRECTORY));
    }

    @Test
    public void shouldReturnCorrectAuthorNameForArticle7() throws Exception {
        assertTrue(txtParser.pullAuthorName(TXT_ARTICLE_7).equals(AUTHOR_ARTICLE_7));
        logger.info(txtParser.pullAuthorName(TXT_ARTICLE_7));
    }

    @Test
    public void shouldReturnCorrectAuthorNameForArticle8() throws Exception {
        assertTrue(txtParser.pullAuthorName(TXT_ARTICLE_8).equals(AUTHOR_ARTICLE_8));
        logger.info(txtParser.pullAuthorName(TXT_ARTICLE_8));
    }

    @Test
    public void shouldReturnCorrectAuthorNameForArticle9() throws Exception {
        assertTrue(txtParser.pullAuthorName(TXT_ARTICLE_9).equals(AUTHOR_ARTICLE_9));
        logger.info(txtParser.pullAuthorName(TXT_ARTICLE_9));
    }

    @Test
    public void shouldReturnTXTFilesFromDirectory() throws ParserException {
        TXTparser.getConcreteTypeFilesFromDirectory(DIRECTORY, TYPE);
        assertEquals(TXTparser.getConcreteTypeFilesFromDirectory(DIRECTORY, TYPE), TXT_FILES);
    }

    @Test
    public void shouldThrowParserException() throws ParserException {
        thrown.expect(ParserException.class);
        TXTparser.getArticles(FAIL_DIRECTORY);
    }


}