package com.epam;


import com.epam.entity.Article;
import com.epam.exception.ParserException;

import java.util.List;

public interface IParser {

    List<Article> getArticles(String directory) throws ParserException;

}
