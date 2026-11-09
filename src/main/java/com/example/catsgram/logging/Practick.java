package com.example.catsgram.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Practick {
    // получает логер с именем класса к которому он относится
    private static final Logger log = LoggerFactory.getLogger(Practick.class);

    public static void main(String[] args) {
        //выводим имя логгера
        System.out.println(log.getName());
    }

}
