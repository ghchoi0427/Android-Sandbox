package com.example.firebase210724;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase210724.domain.Board;

public class ViewBoardActivity extends AppCompatActivity {
    TextView txtBoardTitle;
    TextView txtBoardUser;
    TextView txtBoardContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_board);

        Board board = (Board) getIntent().getSerializableExtra("board");

        txtBoardTitle = findViewById(R.id.txt_board_title);
        txtBoardUser = findViewById(R.id.txt_view_board_user);
        txtBoardContent = findViewById(R.id.txt_view_board_content);

        txtBoardTitle.setText(board.getTitle());
        txtBoardUser.setText(board.getUserId());
        txtBoardContent.setText(board.getContent());
    }
}
