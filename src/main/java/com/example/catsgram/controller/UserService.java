package com.example.catsgram.controller;

import com.example.catsgram.exception.InvalidEmailException;
import com.example.catsgram.exception.PostNotFoundException;
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

    public User findByEmail(String email) {
        Optional<User> usertOpt = users.stream()
                .filter(x -> x.getEmail().equals(email))
                .findFirst();

        if (usertOpt.isEmpty()) {
            String message = "Не найден пост с ID: " + email;
            throw new PostNotFoundException(message);
        }

        return usertOpt.get();
    }


    public User create(User user){
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

    public User update(String email, User user) {
        Optional<User> userOpt = users.stream()
                .filter(x -> email.equalsIgnoreCase(x.getEmail()))
                .findFirst();

        if (userOpt.isEmpty()) {
            String message = "Пользователь не найден: " + email;
            throw new UserNotFoundException(message);
        }

        // если новый email отличается от старого, тогда проверять занятость
        if (!email.equalsIgnoreCase(user.getEmail())
                && users.stream().anyMatch(x -> user.getEmail().equalsIgnoreCase(x.getEmail()))) {
            throw new UserAlreadyExistException("Email уже занят: " + user.getEmail());
        }

        users.remove(userOpt.get());
        users.add(user);
        return user;
    }

    public void delete(String email) {
        Optional<User> userOpt = users.stream()
                .filter(x -> email.equalsIgnoreCase(x.getEmail()))
                .findFirst();

        if (userOpt.isEmpty()) {
            String message = "Пользователь не найден: " + email;
            throw new UserNotFoundException(message);
        }

        users.remove(userOpt.get());
    }
}
