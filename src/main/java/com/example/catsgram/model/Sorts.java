package com.example.catsgram.model;

import java.util.Set;

public final class Sorts {
    public static final String ASC = "ASC";
    public static final String DESC = "DESC";

    public static final Set<String> ALLOWED = Set.of(ASC, DESC);

    private Sorts() {} // запрет на создание экземпляров

    public static boolean isValid(String sort) {
        return sort != null && ALLOWED.contains(sort.toUpperCase());
    }
}