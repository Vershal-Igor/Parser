package com.epam.impl.txt;

import com.epam.IParser;
import com.epam.ParserMaker;
import com.epam.ParserType;
import org.junit.Before;
import org.junit.Test;

import static com.epam.ParserMaker.getParserByName;
import static org.junit.Assert.*;

public class TXTParserTest {
    private static final String DIRECTORY = "src/main/resources/files";
    private static final String TEST_DIRECTORY = "src/test/resources/files";
    private IParser TXTparser;

    @Before
    public void setUp() throws Exception {
        ParserMaker TXTmaker = getParserByName(ParserType.TXT);
        TXTparser = TXTmaker.createParser();
    }

    @Test
    public void parseTXTTets() throws Exception {
        assertEquals(TXTparser.getArticles(DIRECTORY), TXTparser.getArticles(TEST_DIRECTORY));
        System.out.println(TXTparser.getArticles(DIRECTORY));
    }

}