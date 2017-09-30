package com.epam.impl.txt;

import com.epam.entity.Article;
import com.epam.exception.ParserException;
import com.epam.impl.AbstractParser;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class TXTParser extends AbstractParser {
    public static Logger logger = Logger.getLogger(TXTParser.class);
    private static final String FNF_EXCEPTION = "No such file:";
    private static final String IO_EXCEPTION = "Input exception:";
    private static final String TYPE = "txt";
    private static final String FILE_ENCODING = "utf-8";

    public TXTParser() {
        super(TYPE);
    }

    private String pullAuthorName(BufferedReader reader) throws ParserException, IOException {
        String authorString = reader.readLine();
        String[] array = authorString.split(": ");
        if (array.length == 2) {
            String author = array[1].trim();
            return author;
        }
        throw new ParserException("Corrupted author format: " + authorString);
    }


    @Override
    protected List<Article> parse(String directory) throws ParserException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(directory)), Charset.forName(FILE_ENCODING)))) {

            String title = reader.readLine();
            String author = pullAuthorName(reader);
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

