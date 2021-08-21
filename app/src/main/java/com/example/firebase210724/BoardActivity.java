package com.example.firebase210724;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase210724.adapter.BoardAdapter;
import com.example.firebase210724.domain.Board;
import com.example.firebase210724.util.FirebaseDatabaseHandler;
import com.google.firebase.firestore.FirebaseFirestore;

public class BoardActivity extends AppCompatActivity implements BoardAdapter.ViewHolder.OnBoardListener {
    Button btnBoardUpload;
    RecyclerView recyclerBoard;
    BoardAdapter boardAdapter;
    FirebaseDatabaseHandler handler;

    TextView txtViewBoardTitle;
    TextView txtViewBoardUser;
    TextView txtViewBoardContent;

    private boolean isViewMode = false;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        boardAdapter = new BoardAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerBoard = findViewById(R.id.recycler_board);
        recyclerBoard.setLayoutManager(layoutManager);
        recyclerBoard.setAdapter(boardAdapter);

        txtViewBoardTitle = findViewById(R.id.txt_view_board_title);
        txtViewBoardUser = findViewById(R.id.txt_view_board_user);
        txtViewBoardContent = findViewById(R.id.txt_view_board_content);

        handler = new FirebaseDatabaseHandler(FirebaseFirestore.getInstance());

        btnBoardUpload = findViewById(R.id.btn_board_upload);

        loadBoard();

        btnBoardUpload.setOnClickListener(view -> startActivity(new Intent(BoardActivity.this, UploadActivity.class)));

    }

    @Override
    public void onBackPressed() {
        if (isViewMode) {
            setContentView(R.layout.activity_board);
            this.recreate();
            isViewMode = false;
        } else {
            finish();
        }
    }

    private void loadBoard() {
        handler.setBoardList(getApplicationContext(), boardAdapter);
    }

    @Override
    public void onBoardClick(int position) {
        setContentView(R.layout.activity_view_board);
        isViewMode = true;

        txtViewBoardTitle = findViewById(R.id.txt_view_board_title);
        txtViewBoardUser = findViewById(R.id.txt_view_board_user);
        txtViewBoardContent = findViewById(R.id.txt_view_board_content);

        Board board = boardAdapter.getBoardList().get(position);
        txtViewBoardTitle.setText(board.getTitle());
        txtViewBoardUser.setText(board.getUserId());
        txtViewBoardContent.setText(board.getContent());
    }
}
