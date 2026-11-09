package com.example.catsgram.controller;

import com.example.catsgram.logging.Practick;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class SimpleController {
    //создаем лог
    private static final Logger log = LoggerFactory.getLogger(SimpleController.class);

    @GetMapping("/home")
    public String homePage(){
        //логируем факт получения запроса
        log.info("Запрос получен");

        //возвращаем ответ в виде строки
        return "Котограм";
    }
}
