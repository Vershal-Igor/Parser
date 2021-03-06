package com.epam.client;




import com.epam.Parser;
import com.epam.ParserMaker;

import com.epam.ParserType;
import com.epam.exception.ParserException;


import java.io.IOException;



import static com.epam.ParserMaker.getParserByName;


public class Main {
    private static final String DIRECTORY = "src/main/resources/files";

    public static void main(String[] args) throws ParserException, IOException {

        ParserMaker XMLmaker = getParserByName(ParserType.XML);
        Parser XMLparser = XMLmaker.createParser();
        XMLparser.loadArticlesFromDirectory(DIRECTORY);

        ParserMaker JSONmaker = getParserByName(ParserType.JSON);
        Parser JSONParser = JSONmaker.createParser();
        JSONParser.loadArticlesFromDirectory(DIRECTORY);


        ParserMaker TXTmaker = getParserByName(ParserType.TXT);
        Parser TXTParser = TXTmaker.createParser();
        TXTParser.loadArticlesFromDirectory(DIRECTORY);

    }
}
