package com.example.firebase210724.domain;


import com.google.firebase.Timestamp;

public class Board {
    String userId;
    String title;
    String content;
    Timestamp date;

    public Board(String userId, String title, String content, Timestamp date) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.date = date;
    }
}
