package com.example.catsgram.controller;

import com.example.catsgram.model.Post;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PostService {
    private List<Post> posts = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    public List<Post> findAll(){
        log.debug("Текущее количество постов: {}", posts.size());
        return posts;
    }

    public Post create(@RequestBody Post post){
        log.info("Добавлен пост: {}", post);
        posts.add(post);
        return post;
    }
}
