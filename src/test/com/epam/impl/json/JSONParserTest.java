package com.epam.impl.json;

import com.epam.IParser;
import com.epam.ParserMaker;
import com.epam.ParserType;
import com.epam.exception.ParserException;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.epam.ParserMaker.getParserByName;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
//@RunWith(Parameterized.class)
public class JSONParserTest {
    private static Logger logger = Logger.getLogger(JSONParserTest.class);

    private static final String TYPE = "json";

    private static final String DIRECTORY = "src/main/resources/files";
    private static final String TEST_DIRECTORY = "src/test/resources/files";
    private static final String FAIL_DIRECTORY = "src/main/resources/file";

    private static final String JSON_ARTICLE_1 = "src\\main\\resources\\files\\Article1.json";
    private static final String JSON_ARTICLE_4 = "src\\main\\resources\\files\\Article4.json";
    private static final String JSON_ARTICLE_6 = "src\\main\\resources\\files\\Article6.json";

    private IParser JSONparser;


    @Parameterized.Parameter
    public String ArticleFileName;


    @Parameterized.Parameters(name = "{index}: ArticleFileName - {0}")
    public static Object[] data() {
        return new Object[]{
                JSON_ARTICLE_1,
                JSON_ARTICLE_4,
                JSON_ARTICLE_6
        };
    }

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        ParserMaker JSONmaker = getParserByName(ParserType.JSON);
        JSONparser = JSONmaker.createParser();
    }

    @Test
    public void shouldReturnJSONFilesFromDirectory() throws ParserException {
        assertThat(JSONparser.getConcreteTypeFilesFromDirectory(DIRECTORY, TYPE), is(data()));
    }

    @Test
    public void shouldParseJSON() throws Exception {
        assertEquals(JSONparser.getArticles(DIRECTORY), JSONparser.getArticles(TEST_DIRECTORY));
        logger.info(JSONparser.getArticles(DIRECTORY));
    }

    @Test
    public void shouldThrowParserException() throws ParserException {
        thrown.expect(ParserException.class);
        JSONparser.getArticles(FAIL_DIRECTORY);
    }

}