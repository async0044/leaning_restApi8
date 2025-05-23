package com.async.restApi8.dto;


public class ItemDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;

    public ItemDto(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}


