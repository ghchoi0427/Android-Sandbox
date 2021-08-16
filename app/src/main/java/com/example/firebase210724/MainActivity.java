package com.example.firebase210724;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonStorage;
    Button buttonDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStorage = findViewById(R.id.btn_storage);
        buttonDatabase = findViewById(R.id.btn_database);

        buttonStorage.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), StorageActivity.class)));
        buttonDatabase.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), RealtimeDBActivity.class)));
    }
}