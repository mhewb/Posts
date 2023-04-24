package io.m2i.posts.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {

    public static final DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private int id;
    private String title;
    private String author;
    private String content;
//    private LocalDateTime createdAt;
    private String imgUrl;

    public Post() {
    }

    public Post(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
//        this.createdAt = createdAt;
    }

    public Post(int id, String title, String author, String content, String imgUrl) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
//        this.createdAt = createdAt;
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Post{" +
                "\n id=" + id +
                "\n title='" + title + '\'' +
                "\n author='" + author + '\'' +
                "\n content='" + content + '\'' +
                "\n imgUrl='" + imgUrl + '\'' +
                '}';
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
}