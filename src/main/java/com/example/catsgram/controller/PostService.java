package com.example.catsgram.controller;

import com.example.catsgram.model.Post;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class PostService {
    private List<Post> posts = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(PostController.class);


    // http://localhost:8080/posts?size=10&sort=ASC&from=0
    // показать 10 постов, начать с начала (старых), с первого поста
    public List<Post> findAll(int size, String sort, int page){
        // size - сколько постов показать, размер
        // sort - сортировка с начала или конца
        // page - страница начала (size постов на 1 страницу)
        log.debug("Запрос на показ {} постов, отсортированных {}, начиная с {}",
                size, sort, page);

        // создания компратора (правила сортировки), по умолчанию от старых к новым
        Comparator<Post> comparator = Comparator.comparing(Post::getCreationDate);

        //если придет значение "DESC" или "desc" сортировка от новых к старым
        if ("DESC".equalsIgnoreCase(sort)) {
            comparator = comparator.reversed();
        }

        int offset = page * size;

        return posts.stream()
                .sorted(comparator) // сортировка
                .skip(offset)   // пропускаем "from" постов
                .limit(size)  // берем "size" постов
                .collect(Collectors.toList()); // сборка обратно в список
    }

    public Post create(@RequestBody Post post){
        log.info("Добавлен пост: {}", post);
        posts.add(post);
        return post;
    }

    public Optional<Post> findById(@PathVariable int postId){

        return posts.stream()
                .filter(x -> x.getId() == postId)
                .findFirst();
    }

}
