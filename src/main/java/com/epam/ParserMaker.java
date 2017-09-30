package com.epam;


import com.epam.impl.json.JSONParserMaker;
import com.epam.impl.txt.TXTParserMaker;
import com.epam.impl.xml.XMLParserMaker;
import org.apache.log4j.Logger;

public interface ParserMaker {
    public static Logger logger = Logger.getLogger(ParserMaker.class);
    static final String EXCEPTION = "Can't find such type: ";

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
                logger.error(EXCEPTION + type);
                throw new RuntimeException(EXCEPTION + type);
        }
    }
}
