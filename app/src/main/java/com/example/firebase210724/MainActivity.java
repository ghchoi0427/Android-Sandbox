package com.example.firebase210724;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBoard = findViewById(R.id.btn_board);

        btnBoard.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), BoardActivity.class)));
    }
}