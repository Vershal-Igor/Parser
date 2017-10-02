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

    private static final String DIRECTORY = "src/main/resources/files";
    private static final String TEST_DIRECTORY = "src/test/resources/files";
    private static final String FAIL_DIRECTORY = "src/main/resources/file";
    private IParser JSONparser;

    @Before
    public void setUp() throws Exception {
        ParserMaker TXTmaker = getParserByName(ParserType.JSON);
        JSONparser = TXTmaker.createParser();
    }

    @Test
    public void parseJSONTest() throws Exception {
        assertEquals(JSONparser.getArticles(DIRECTORY), JSONparser.getArticles(TEST_DIRECTORY));
        logger.info(JSONparser.getArticles(DIRECTORY));
    }

    @Test(expected = ParserException.class)
    public void throwParserExceptionTest() throws ParserException {
        JSONparser.getArticles(FAIL_DIRECTORY);
        Assert.fail("ParserException should be thrown");
    }

}