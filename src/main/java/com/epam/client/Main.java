package com.epam.client;


import com.epam.IParser;
import com.epam.ParserMaker;

import com.epam.ParserType;
import com.epam.exception.ParserException;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.epam.ParserMaker.getParserByName;


public class Main {
    private static final String directory = "src/main/resources/files";

    public static void main(String[] args) throws ParserException {

        ParserMaker XMLmaker = getParserByName(ParserType.XML);
        IParser XMLparser = XMLmaker.createParser();
        System.out.println(XMLparser.getArticles(directory));

        ParserMaker JSONmaker = getParserByName(ParserType.JSON);
        IParser JSONParser = JSONmaker.createParser();
        System.out.println(JSONParser.getArticles(directory));


        ParserMaker TXTmaker = getParserByName(ParserType.TXT);
        IParser TXTParser = TXTmaker.createParser();
        System.out.println(TXTParser.getArticles(directory));


    }
}
