package com.example.firebase210724;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase210724.domain.Board;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadActivity extends AppCompatActivity {

    EditText editTitle;
    EditText editUser;
    EditText editContent;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        db = FirebaseDatabase.getInstance().getReference();

        editTitle = findViewById(R.id.edit_upload_title);
        editUser = findViewById(R.id.edit_upload_user);
        editContent = findViewById(R.id.edit_upload_content);
    }

    private Board createBoard() {
        String title = editTitle.getText().toString();
        String user = editUser.getText().toString();
        String content = editContent.getText().toString();

        return new Board(user, content, title);
    }

    private void upload(Board board) {

    }
}
