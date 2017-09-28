package com.epam.impl.xml;


import com.epam.IParser;
import com.epam.ParserMaker;


public class XMLParserMaker implements ParserMaker {
    public IParser createParser() {
        return new XMLParser();
    }
}
