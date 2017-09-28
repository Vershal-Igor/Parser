package com.epam.entity;


import com.epam.adapter.StringAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;


@XmlRootElement(name = "article")
public class Article implements Serializable {

    private String title;
    private String author;

    public Article() {
    }

    public Article(String title, String author) {
        this.title = title;
        this.author = author;
    }


    public String getTitle() {
        return title;
    }

    @XmlElement(name = "title", required = true)
    @XmlJavaTypeAdapter(StringAdapter.class)
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    @XmlElement(name = "author", defaultValue = "Unknown")
    @XmlJavaTypeAdapter(StringAdapter.class)
    public void setAuthor(String author) {
        this.author = author;
    }


    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (title != null ? !title.equals(article.title) : article.title != null) return false;
        return author != null ? author.equals(article.author) : article.author == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}
