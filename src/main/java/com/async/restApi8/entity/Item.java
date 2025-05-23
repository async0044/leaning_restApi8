package com.async.restApi8.entity;

import jakarta.persistence.*;

@Entity
@Table(name="8item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String title;
    private String content;
    private String author;

    public Item() {}

    public Item(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
