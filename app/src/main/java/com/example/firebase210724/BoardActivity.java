package com.example.firebase210724;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BoardActivity extends AppCompatActivity {
    Button btnBoardUpload;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        btnBoardUpload = findViewById(R.id.btn_board_upload);

        btnBoardUpload.setOnClickListener(view -> startActivity(new Intent(BoardActivity.this, UploadActivity.class)));

    }
}
