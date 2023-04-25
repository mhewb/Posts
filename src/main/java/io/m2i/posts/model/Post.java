package io.m2i.posts.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {

    public static final DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private int id;
    private String title;
    private String author;
    private String content;
    private String imgUrl;
    private Category category;

    public Post() {
    }

    public Post(String title, String author, String content, Category category) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.category = category;
    }

    public Post(int id, String title, String author, String content, String imgUrl, Category category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.imgUrl = imgUrl;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}