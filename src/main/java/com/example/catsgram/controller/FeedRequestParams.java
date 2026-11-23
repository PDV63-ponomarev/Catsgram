package com.example.catsgram.controller;

import lombok.Data;
import java.util.List;

@Data
public class FeedRequestParams {
    private String sort;
    private int size;
    private List<String> friends;
}

