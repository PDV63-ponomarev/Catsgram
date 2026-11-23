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
    public List<Post> findAll(@RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "DESC") String sort,
                              @RequestParam(defaultValue = "0") int from){
        return postService.findAll(size, sort, from);
    }

    @PostMapping("/post")
    public Post create(@RequestBody Post post){
        return postService.create(post);
    }

    @GetMapping("(/post/{postId}")
    public Optional<Post> findById(@PathVariable int postId){
        return postService.findById(postId);
    }

}
