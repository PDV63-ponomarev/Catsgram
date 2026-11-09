package com.example.catsgram.controller;

import com.example.catsgram.exception.InvalidEmailException;
import com.example.catsgram.exception.UserAlreadyExistException;
import com.example.catsgram.exception.UserNotFoundException;
import com.example.catsgram.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final Map<String, User> users = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping // получить всех пользователей
    public List<User> findAll(){
        return new ArrayList<>(users.values());
    }

    @GetMapping("/{email}")  //просмотр пользователя
    public User findByEmail(@PathVariable String email) {
        User user = users.get(email);
        if (user == null) {
            throw new UserNotFoundException("Пользователь не найден: " + email);
        }
        return user;
    }

    @PostMapping // создание пользователя
    public User create(@RequestBody User user){
        // 1. Проверка email на пустоту/null
        String email = user.getEmail();
        if (email == null || email.isBlank()) {
            throw new InvalidEmailException("Email не может быть пустым");
        }

        // 2. Проверка на уникальность
        if (users.containsKey(email)) {
            throw new UserAlreadyExistException("Пользователь с email " + email + " уже существует");
        }

        log.info("Добавлен пользователь: {}", user.getNickname());

        // 3. Сохранение
        users.put(email, user);
        return user;
    }

    @PutMapping("/{email}")  //изменение почты у пользователя
    public User update(@PathVariable String email, @RequestBody User user) {
        if (!users.containsKey(email)) {
            throw new UserNotFoundException("Пользователь не найден: " + email);
        }
        // при смене email — проверяем уникальность нового
        if (!email.equals(user.getEmail()) && users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("Email уже существует: " + user.getEmail());
        }
        users.remove(email);
        users.put(user.getEmail(), user);
        return user;
    }

    @DeleteMapping("/{email}")   // удаление пользователя
    public void delete(@PathVariable String email) {
        users.remove(email);
    }
}

