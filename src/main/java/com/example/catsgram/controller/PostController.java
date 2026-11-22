package com.example.catsgram.controller;

import com.example.catsgram.model.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
    private final  PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> findAll(){
        return postService.findAll();
    }

    @PostMapping(value = "/post")
    public Post create(@RequestBody Post post){
        return postService.create(post);
    }

    @GetMapping("/posts/{postId}")
    public Optional<Post> findById(@PathVariable int postId){
        return postService.findById(postId);
    }
}
