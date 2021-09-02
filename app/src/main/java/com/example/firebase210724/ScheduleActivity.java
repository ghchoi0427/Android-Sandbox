package com.example.firebase210724;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase210724.util.FirebaseDatabaseHandler;
import com.example.firebase210724.util.ScheduleFactory;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class ScheduleActivity extends AppCompatActivity {
    CalendarView calendarView;
    FirebaseDatabaseHandler handler;
    EditText txtTodo;
    Button btnScheduleUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        handler = new FirebaseDatabaseHandler(FirebaseFirestore.getInstance());

        calendarView = findViewById(R.id.calendarView);
        txtTodo = findViewById(R.id.txt_todo);
        btnScheduleUpload = findViewById(R.id.btn_schedule_upload);
        calendarView.setOnDateChangeListener(onDateChangeListener);
        calendarView.setOnDateChangeListener((calendarView, year, month, dayOfMonth) -> {

        });

        btnScheduleUpload.setOnClickListener(view -> {
            String date = new Date(calendarView.getDate()).toString();
            String todo = txtTodo.getText().toString();
            handler.postSchedule(getApplicationContext(), ScheduleFactory.createSchedule(date, todo, handler.getUserId()));
        });

    }

    CalendarView.OnDateChangeListener onDateChangeListener = (calendarView, year, month, dayOfMonth) -> {
        handler.getSchedule(txtTodo, year, month, dayOfMonth, handler.getUserId());

    };

    private void updateCalendar() {

    }
}
