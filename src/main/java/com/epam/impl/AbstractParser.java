package com.epam.impl;

import com.epam.IParser;
import com.epam.entity.Article;
import com.epam.exception.ParserException;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public abstract class AbstractParser implements IParser {
    private static final String FORMAT_PATTERN = "{*.%s}";
    private String type;

    public AbstractParser(String type) {
        this.type = type;
    }

    public String[] getConcreteTypeFilesFromDirectory(String directory, String type) throws ParserException {
        String[] fileNames;

        try {
            Path path = Paths.get(directory);
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path, String.format(FORMAT_PATTERN, type))) {

                fileNames = StreamSupport
                        .stream(directoryStream.spliterator(), false)
                        .map(path1 -> path1.toString()).toArray(value -> new String[value]);
            }
            return fileNames;
        } catch (IOException e) {
            throw new ParserException(e.getMessage());
        }
    }

    public  List<Article> getArticles(String directory) throws ParserException{
        List<Article> articleList = new ArrayList<>();

        String[] names = getConcreteTypeFilesFromDirectory(directory, type);

        for (String name : names) {
            List<Article> list = parse(name);
            articleList.addAll(list);
        }
        return articleList;
    }

    protected abstract List<Article> parse(String directory) throws ParserException;
}
