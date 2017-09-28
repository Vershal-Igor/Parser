package com.epam.impl.txt;

import com.epam.IParser;
import com.epam.ParserMaker;

public class TXTParserMaker implements ParserMaker {
    @Override
    public IParser createParser() {
        return new TXTParser();
    }
}
