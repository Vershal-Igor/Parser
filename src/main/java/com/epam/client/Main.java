package com.epam.client;


import com.epam.IParser;
import com.epam.ParserMaker;

import com.epam.ParserType;
import com.epam.exception.ParserException;

import static com.epam.ParserMaker.getParserByName;


public class Main {
    private static final String DIRECTORY = "src/main/resources/files";

    public static void main(String[] args) throws ParserException {

        ParserMaker XMLmaker = getParserByName(ParserType.XML);
        IParser XMLparser = XMLmaker.createParser();
        XMLparser.getArticles(DIRECTORY);

        ParserMaker JSONmaker = getParserByName(ParserType.JSON);
        IParser JSONParser = JSONmaker.createParser();
        JSONParser.getArticles(DIRECTORY);


        ParserMaker TXTmaker = getParserByName(ParserType.TXT);
        IParser TXTParser = TXTmaker.createParser();
        TXTParser.getArticles(DIRECTORY);

    }
}
