package com.epam.impl.json;

import com.epam.IParser;
import com.epam.ParserMaker;
import com.epam.ParserType;
import com.epam.exception.ParserException;
import com.epam.impl.AbstractParser;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.epam.ParserMaker.getParserByName;
import static org.junit.Assert.*;

public class JSONParserTest {
    private static Logger logger = Logger.getLogger(JSONParserTest.class);

    private static final String TYPE = "json";

    private static final String DIRECTORY = "src/main/resources/files";
    private static final String TEST_DIRECTORY = "src/test/resources/files";
    private static final String FAIL_DIRECTORY = "src/main/resources/file";

    private static final String[] JSON_FILES = {"src\\main\\resources\\files\\Article1.json",
            "src\\main\\resources\\files\\Article4.json","src\\main\\resources\\files\\Article6.json"};
    private IParser JSONparser;

    @Before
    public void setUp() throws Exception {
        ParserMaker JSONmaker = getParserByName(ParserType.JSON);
        JSONparser = JSONmaker.createParser();
    }

    @Test
    public void parseJSONTest() throws Exception {
        assertEquals(JSONparser.getArticles(DIRECTORY), JSONparser.getArticles(TEST_DIRECTORY));
        logger.info(JSONparser.getArticles(DIRECTORY));
    }

    @Test
    public void getJSONFilesFromDirectoryTest() throws ParserException {
        JSONparser.getConcreteTypeFilesFromDirectory(DIRECTORY,TYPE);
        assertEquals(JSONparser.getConcreteTypeFilesFromDirectory(DIRECTORY,TYPE),JSON_FILES);
    }

    @Test(expected = ParserException.class)
    public void throwParserExceptionTest() throws ParserException {
        JSONparser.getArticles(FAIL_DIRECTORY);
        Assert.fail("ParserException should be thrown");
    }

}