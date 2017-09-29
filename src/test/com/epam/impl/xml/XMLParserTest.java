package com.epam.impl.xml;

import com.epam.IParser;
import com.epam.ParserMaker;
import com.epam.ParserType;
import org.junit.Before;
import org.junit.Test;

import static com.epam.ParserMaker.getParserByName;
import static org.junit.Assert.*;

public class XMLParserTest {
    private static final String DIRECTORY = "src/main/resources/files";
    private static final String TEST_DIRECTORY = "src/test/resources/files";
    private IParser XMLparser;

    @Before
    public void setUp() throws Exception {
        ParserMaker XMLmaker = getParserByName(ParserType.XML);
        XMLparser = XMLmaker.createParser();
    }

    @Test
    public void parseXMLTest() throws Exception {
        assertEquals(XMLparser.getArticles(DIRECTORY), XMLparser.getArticles(TEST_DIRECTORY));
    }

}