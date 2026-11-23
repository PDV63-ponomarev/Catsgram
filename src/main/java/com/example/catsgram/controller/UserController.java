package com.example.catsgram.controller;

import com.example.catsgram.model.Post;
import com.example.catsgram.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{email}")
    public Optional<User> findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @PostMapping // создание пользователя
    public User create(@RequestBody User user){
        return  userService.create(user);
    }

    @PutMapping("/{email}")  //изменение почты у пользователя
    public User update(@PathVariable String email, @RequestBody User user) {
        return userService.update(email, user);
    }

    @DeleteMapping("/{email}")   // удаление пользователя
    public void delete(@PathVariable String email) {
        userService.delete(email);
    }
}

