package com.example.catsgram.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public class Post {

    private Integer id;
    private final String author; // автор

    private Instant creationDate; //дата создания

    private String description; // описание
    private String photoUrl; // url фотографии

    public Post(Integer id, String author, Instant creationDate, String description, String photoUrl){
        this.id = id;
        this.author = author;
        this.creationDate = creationDate != null ? creationDate : Instant.now();
        this.description = description;
        this.photoUrl = photoUrl;
    }

    public Instant getCreationDate(){
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
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
