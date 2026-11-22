package com.example.catsgram.model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Instant;
import java.util.Optional;

public class Post {

    private Integer id;
    private final String author; // автор
    private final Instant creationDate = Instant.now(); //дата создания
    private String description; // описание
    private String photoUrl; // url фотографии

    public Post(Integer id, String author, String description, String photoUrl){
        this.id = id;
        this.author = author;
        this.description = description;
        this.photoUrl = photoUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor(){
        return author;
    }

    public Instant getCreationDate(){
        return creationDate;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
