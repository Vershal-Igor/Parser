package com.epam.impl.json;

import com.epam.IParser;
import com.epam.ParserMaker;
import com.epam.ParserType;
import com.epam.exception.ParserException;
import com.epam.impl.AbstractParser;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        ParserMaker JSONmaker = getParserByName(ParserType.JSON);
        JSONparser = JSONmaker.createParser();
    }

    @Test
    public void shouldParseJSON() throws Exception {
        assertEquals(JSONparser.getArticles(DIRECTORY), JSONparser.getArticles(TEST_DIRECTORY));
        logger.info(JSONparser.getArticles(DIRECTORY));
    }

    @Test
    public void shouldReturnJSONFilesFromDirectory() throws ParserException {
        JSONparser.getConcreteTypeFilesFromDirectory(DIRECTORY,TYPE);
        assertEquals(JSONparser.getConcreteTypeFilesFromDirectory(DIRECTORY,TYPE),JSON_FILES);
    }

    @Test
    public void shouldThrowParserException() throws ParserException {
        thrown.expect(ParserException.class);
        JSONparser.getArticles(FAIL_DIRECTORY);
    }

}