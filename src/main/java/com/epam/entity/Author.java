package com.epam.entity;

import javax.xml.bind.annotation.XmlElement;

public class Author {
    String name;
    Article article;

    public String getName() {
        return name;
    }


    public Article getArticle() {
        return article;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }


    @XmlElement
    public void setArticle(Article article) {
        this.article = article;
    }


}
