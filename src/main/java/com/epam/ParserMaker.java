package com.epam;



import com.epam.impl.json.JSONParserMaker;
import com.epam.impl.txt.TXTParserMaker;
import com.epam.impl.xml.XMLParserMaker;

public interface ParserMaker {
    IParser createParser();

    static ParserMaker getParserByName(ParserType type) {
        switch (type) {
            case XML:
                return new XMLParserMaker();
            case JSON:
                return new JSONParserMaker();
            case TXT:
                return new TXTParserMaker();
            default:
                throw new RuntimeException("Can't find such type" + type);
        }
    }
}
