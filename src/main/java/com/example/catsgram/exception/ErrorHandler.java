package com.example.catsgram.exception;

import com.example.catsgram.controller.UserController;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import org.slf4j.Logger;

@RestControllerAdvice
public class ErrorHandler {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public Map<String, String> handleNotFoundPost(PostNotFoundException e) {
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public Map<String, String> handleNotFoundUser(UserNotFoundException e) {
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT) // 409
    public Map<String, String> handleUserExists(UserAlreadyExistException e) {
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler(InvalidEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public Map<String, String> handleInvalidEmail(InvalidEmailException e) {
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler(IncorrectParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public Map<String, String> handleIncorrectParameter(String message) {
        return Map.of("error", "Ошибка с полем: " + message);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public String throwable(Throwable ex) {
        log.error("Непредвиденная ошибка", ex);
        return "Произошла непредвиденная ошибка";
    }

}