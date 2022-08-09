package com.ll.exam.sbb;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Article {
    private static int lastId = 0;
    private int id;
    private final String title;
    private final String body;

    Article(String title, String body) {
        this(++lastId, title, body);
    }
}
