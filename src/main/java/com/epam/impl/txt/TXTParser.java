package com.epam.impl.txt;

import com.epam.entity.Article;
import com.epam.exception.ParserException;
import com.epam.impl.AbstractParser;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;


public class TXTParser extends AbstractParser {
    private static Logger logger = Logger.getLogger(TXTParser.class);
    private static final String FNF_EXCEPTION = "No such file:";
    private static final String AUTHOR_PATERN = "Written by:";
    private static final String DEFAULT_ELEMENT = "UNKNOWN";
    private static final String IO_EXCEPTION = "Input exception:";
    private static final String TYPE = "txt";
    private static final String FILE_ENCODING = "utf-8";


    public TXTParser() {
        super(TYPE);
    }


    private String returnAuthorName(String directory) throws ParserException {
        String author = null;
        try {
            Scanner scanner = new Scanner(new FileReader(directory));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith(AUTHOR_PATERN)) {
                    String[] array = line.split(": ");
                    author = array[1].trim();
                }
            }
            return author;
        } catch (FileNotFoundException e) {
            logger.error(FNF_EXCEPTION + directory, e);
            throw new ParserException(FNF_EXCEPTION + directory, e);
        }

    }

    private String pullAuthorName(String directory) throws ParserException {
        if (returnAuthorName(directory) == null) {
            return DEFAULT_ELEMENT;
        }
        if (returnAuthorName(directory) != null) {
            return returnAuthorName(directory);
        }
        return DEFAULT_ELEMENT;
    }


    @Override
    protected List<Article> parse(String directory) throws ParserException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(directory)),
                Charset.forName(FILE_ENCODING)))) {

            String title = reader.readLine();
            String author = pullAuthorName(directory);

            ArrayList<Article> articles = new ArrayList<>();
            articles.add(new Article(title, author));

            return articles;
        } catch (FileNotFoundException e) {
            logger.error(FNF_EXCEPTION + directory, e);
            throw new ParserException(FNF_EXCEPTION + directory, e);
        } catch (IOException e) {
            logger.error(IO_EXCEPTION, e);
            throw new ParserException(IO_EXCEPTION, e);
        }
    }
}

