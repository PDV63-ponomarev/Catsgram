package com.example.catsgram.controller;

import com.example.catsgram.exception.IncorrectParameterException;
import com.example.catsgram.exception.PostNotFoundException;
import com.example.catsgram.model.Post;

import com.example.catsgram.model.Sorts;
import lombok.ToString;
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
                              @RequestParam(defaultValue = "0") int page){
        if (!Sorts.isValid(sort)) {
            throw new IncorrectParameterException("sort");
        }
        if (page<0){
            throw new IncorrectParameterException("page");
        }
        if (size <= 0){
            throw new IncorrectParameterException("size");
        }

        return postService.findAll(size, sort, page);
    }

    @PostMapping("/post")
    public Post create(@RequestBody Post post){

        return postService.create(post);
    }

    @GetMapping("(/post/{postId}")
    public Post findById(@PathVariable int postId){
        return postService.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Не найден пост с ID: " + postId));
    }

}
