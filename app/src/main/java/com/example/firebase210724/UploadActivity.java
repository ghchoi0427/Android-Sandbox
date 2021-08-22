package com.example.firebase210724;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase210724.util.FirebaseDatabaseHandler;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class UploadActivity extends AppCompatActivity {

    EditText editTitle;
    EditText editContent;
    Button btnUpload;
    FirebaseDatabaseHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        editTitle = findViewById(R.id.edit_upload_title);
        editContent = findViewById(R.id.edit_upload_content);
        btnUpload = findViewById(R.id.btn_upload_post);

        handler = new FirebaseDatabaseHandler(FirebaseFirestore.getInstance());

        btnUpload.setOnClickListener(view -> {
            postBoard(editTitle.getText().toString(), editContent.getText().toString(), FirebaseAuth.getInstance().getUid());
            startActivity(new Intent(this, BoardActivity.class));
            finish();
        });

    }

    public void postBoard(String title, String content, String userId) {
        handler.post(getApplicationContext(), title, content, userId);
    }

}
