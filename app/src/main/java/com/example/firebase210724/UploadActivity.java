package com.example.firebase210724;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase210724.domain.Board;
import com.example.firebase210724.util.FirebaseDatabaseHandler;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class UploadActivity extends AppCompatActivity {

    EditText editTitle;
    EditText editUser;
    EditText editContent;
    FirebaseDatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        editTitle = findViewById(R.id.edit_upload_title);
        editUser = findViewById(R.id.edit_upload_user);
        editContent = findViewById(R.id.edit_upload_content);

        handler = new FirebaseDatabaseHandler(FirebaseFirestore.getInstance());
    }

    private Board createBoard() {
        String title = editTitle.getText().toString();
        String user = editUser.getText().toString();
        String content = editContent.getText().toString();

        return new Board(user, content, title);
    }

    public void postBoard(String title, String content, String userId) {
        handler.post(getApplicationContext(), title, content, userId);
    }

}
