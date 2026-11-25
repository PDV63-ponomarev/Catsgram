package com.example.catsgram.exception;

public class IncorrectParameterException extends RuntimeException {
    public IncorrectParameterException(String message) {
        super("Ошибка с полем " + message);
    }
}
