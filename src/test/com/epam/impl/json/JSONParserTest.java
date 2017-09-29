package com.epam.impl.json;

import com.epam.IParser;
import com.epam.ParserMaker;
import com.epam.ParserType;
import com.epam.impl.AbstractParser;
import org.junit.Before;
import org.junit.Test;

import static com.epam.ParserMaker.getParserByName;
import static org.junit.Assert.*;

public class JSONParserTest {
    private static final String DIRECTORY = "src/main/resources/files";
    private static final String TEST_DIRECTORY = "src/test/resources/files";
    private static final String TYPE = "json";
    private IParser JSONparser;

    @Before
    public void setUp() throws Exception {
        ParserMaker TXTmaker = getParserByName(ParserType.JSON);
        JSONparser = TXTmaker.createParser();
    }

    @Test
    public void parseJSONTest() throws Exception {
        assertEquals(JSONparser.getArticles(DIRECTORY), JSONparser.getArticles(TEST_DIRECTORY));
        System.out.println(JSONparser.getArticles(DIRECTORY));
    }

}