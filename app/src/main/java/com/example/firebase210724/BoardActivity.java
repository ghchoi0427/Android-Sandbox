package com.example.firebase210724;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase210724.adapter.BoardAdapter;
import com.example.firebase210724.util.FirebaseDatabaseHandler;
import com.google.firebase.firestore.FirebaseFirestore;

public class BoardActivity extends AppCompatActivity {
    Button btnBoardUpload;
    RecyclerView recyclerBoard;
    BoardAdapter boardAdapter;
    FirebaseDatabaseHandler handler;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        boardAdapter = new BoardAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerBoard = findViewById(R.id.recycler_board);
        recyclerBoard.setLayoutManager(layoutManager);
        recyclerBoard.setAdapter(boardAdapter);

        handler = new FirebaseDatabaseHandler(FirebaseFirestore.getInstance());

        btnBoardUpload = findViewById(R.id.btn_board_upload);

        loadBoard();

        btnBoardUpload.setOnClickListener(view -> startActivity(new Intent(BoardActivity.this, UploadActivity.class)));

    }

    private void loadBoard() {
        handler.setBoardList(getApplicationContext(), boardAdapter);
    }
}
