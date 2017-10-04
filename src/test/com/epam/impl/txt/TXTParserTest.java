package com.epam.impl.txt;

import com.epam.Loader;
import com.epam.Parser;
import com.epam.ParserMaker;
import com.epam.ParserType;
import com.epam.entity.Article;
import com.epam.exception.ParserException;
import org.apache.log4j.Logger;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static com.epam.ParserMaker.getParserByName;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TXTParserTest {
    private static Logger logger = Logger.getLogger(TXTParserTest.class);

    private static final String TYPE = "txt";


    private Parser TXTparser;
    private TXTParser txtParser;

    public static Object[] txtArticlesFiles() {
        return new Object[]{
                Loader.getTxtArticle7(),
                Loader.getTxtArticle8(),
                Loader.getTxtArticle9()
        };
    }


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        ParserMaker TXTmaker = getParserByName(ParserType.TXT);
        TXTparser = TXTmaker.createParser();
        txtParser = new TXTParser();
    }

    @Test
    public void shouldReturnTXTFilesFromDirectory() throws ParserException {
        String[] expected;
        Matcher<Object[]> actual;

        expected = Loader.getInstance().loadFilesFromDirectoryByType(Loader.getDirectory(), TYPE);
        actual = is(txtArticlesFiles());

        assertThat(expected, actual);
        logger.info(actual);
    }

    @Test
    public void shouldParseTXT() throws Exception {
        List<Article> expected;
        List<Article> actual;

        expected = TXTparser.loadArticlesFromDirectory(Loader.getDirectory());
        actual = TXTparser.loadArticlesFromDirectory(Loader.getTestDirectory());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectAuthorNameForArticle7() throws Exception {
        String expectedAuthorName;
        String actualAuthorName;

        expectedAuthorName = txtParser.pullAuthorName(Loader.getTxtArticle7());
        actualAuthorName = Loader.getAuthorArticle7();

        assertEquals(expectedAuthorName, actualAuthorName);

        logger.info(expectedAuthorName);
    }

    @Test
    public void shouldReturnCorrectAuthorNameForArticle8() throws Exception {
        String expectedAuthorName;
        String actualAuthorName;

        expectedAuthorName = txtParser.pullAuthorName(Loader.getTxtArticle8());
        actualAuthorName = Loader.getAuthorArticle8();

        assertEquals(expectedAuthorName, actualAuthorName);

        logger.info(expectedAuthorName);
    }

    @Test
    public void shouldReturnCorrectAuthorNameForArticle9() throws Exception {
        String expectedAuthorName;
        String actualAuthorName;

        expectedAuthorName = txtParser.pullAuthorName(Loader.getTxtArticle9());
        actualAuthorName = Loader.getDefaultElemenent();

        assertEquals(expectedAuthorName, actualAuthorName);

        logger.info(expectedAuthorName);
    }

    @Test
    public void shouldThrowParserException() throws ParserException {
        thrown.expect(ParserException.class);
        TXTparser.loadArticlesFromDirectory(Loader.getFailDirectory());
    }


}