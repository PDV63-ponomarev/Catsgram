package com.example.catsgram.controller;

import com.example.catsgram.model.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/feed")
public class PostFeedController {


//  "{\"sort\":\"desc\",\"size\":3,\"friends\":[\"puss@boots.com\",\"cat@dogs.net\",\"caty@catss.net\"]}";

    private final ObjectMapper objectMapper;

    // Внедряем ObjectMapper через конструктор
    public PostFeedController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/friends")
    public List<Post> getFriendsFeed(@RequestBody String rawBody) {
        try {
            PostService postService = new PostService();

            // 1. Превращаем "строку с экранированным JSON" в Java-объект параметров
            // ObjectMapper понимает, что это строка, содержащая JSON, и парсит её.
            FeedRequestParams params = objectMapper.readValue(rawBody, FeedRequestParams.class);

            // 2. Теперь  есть нормальные поля (params.getSort(), params.getFriends() и т.д.)
//             List<Post> posts = postService.findAll(params.getFriends(), params.getSort(), params.getSize());

           // Если нужно просто продемонстрировать работу ObjectMapper:
//             return posts;

            return List.of();


        } catch (Exception e) {
            throw new RuntimeException("Ошибка при чтении параметров", e);
        }
    }
}