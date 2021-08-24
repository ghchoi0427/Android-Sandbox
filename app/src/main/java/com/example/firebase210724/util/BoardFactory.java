package com.example.firebase210724.util;

import com.example.firebase210724.domain.Board;
import com.google.firebase.Timestamp;

import java.util.Date;

public class BoardFactory {

    public static Board createBoard(String title, String userId, String content) {
        Board board = new Board();

        board.setContent(content);
        board.setDate(new Timestamp(new Date()));
        board.setTitle(title);
        board.setUserId(userId);

        return board;
    }
}
