package com.epam.impl.json;

import com.epam.IParser;
import com.epam.ParserMaker;

public class JSONParserMaker implements ParserMaker {
    @Override
    public IParser createParser() {
        return new JSONParser();
    }
}
