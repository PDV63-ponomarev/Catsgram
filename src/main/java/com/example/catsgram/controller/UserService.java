package com.example.catsgram.controller;

import com.example.catsgram.exception.InvalidEmailException;
import com.example.catsgram.exception.UserAlreadyExistException;
import com.example.catsgram.exception.UserNotFoundException;
import com.example.catsgram.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public List<User> findAll(){
        log.debug("Текущее количество пользователей: {}", users.size());
        return users;
    }

    public Optional<User> findByEmail(@PathVariable String email) {
        return users.stream()
                .filter(x -> x.getEmail().equals(email))
                .findFirst();
    }


    public User create(@RequestBody User user){
        String email = user.getEmail();
        if (email == null || email.isBlank()) {
            throw new InvalidEmailException("Email не может быть пустым");
        }

        if  (users.stream().anyMatch(x -> email.equalsIgnoreCase(x.getEmail()))) {
            throw new UserAlreadyExistException("Пользователь с email " + email + " уже существует");
        }

        log.info("Добавлен пользователь: {}", user.getNickname());

        users.add(user);
        return user;
    }

    public User update(@PathVariable String email, @RequestBody User user) {
        if  (users.stream().noneMatch(x -> email.equalsIgnoreCase(x.getEmail()))) {
            throw new UserNotFoundException("Пользователь не найден: " + email);
        }

        // при смене email — проверяем уникальность нового
        if  (users.stream().anyMatch(x -> email.equalsIgnoreCase(x.getEmail()))) {
            throw new UserAlreadyExistException("Email уже существует: " + user.getEmail());
        }

        users.remove(email);
        users.add(user);
        return user;
    }

    public void delete(@PathVariable String email) {
        users.remove(email);
    }
}
