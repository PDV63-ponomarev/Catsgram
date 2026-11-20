package com.example.catsgram.controller;

import com.example.catsgram.exception.InvalidEmailException;
import com.example.catsgram.exception.UserAlreadyExistException;
import com.example.catsgram.exception.UserNotFoundException;
import com.example.catsgram.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public User findByEmail(@PathVariable String email) {
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

