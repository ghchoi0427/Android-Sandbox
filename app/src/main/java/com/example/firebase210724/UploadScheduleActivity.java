package com.example.firebase210724;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase210724.util.FirebaseDatabaseHandler;
import com.example.firebase210724.util.ScheduleFactory;
import com.google.firebase.firestore.FirebaseFirestore;

public class UploadScheduleActivity extends AppCompatActivity {
    Button btnUploadTodo;
    EditText editUploadTodo;
    String date;
    FirebaseDatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_schedule);
        btnUploadTodo = findViewById(R.id.btn_upload_todo);
        editUploadTodo = findViewById(R.id.edit_upload_todo);
        date = getIntent().getStringExtra("date");
        handler = new FirebaseDatabaseHandler(FirebaseFirestore.getInstance());

        btnUploadTodo.setOnClickListener(view -> {
            String todo = editUploadTodo.getText().toString();
            handler.postSchedule(getApplicationContext(), ScheduleFactory.createSchedule(date, todo, handler.getUserId()));
            finish();
        });
    }
}
