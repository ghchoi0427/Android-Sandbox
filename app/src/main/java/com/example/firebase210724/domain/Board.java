package com.example.firebase210724.domain;

public class Board {
    String userId;
    String title;
    String content;

    public Board(String userId, String content, String title) {
        this.userId = userId;
        this.content = content;
        this.title = title;
    }
}
