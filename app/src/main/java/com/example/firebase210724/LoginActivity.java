package com.example.firebase210724;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText editId;
    EditText editPassword;
    Button btnLogin;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editId = findViewById(R.id.edit_login_id);
        editPassword = findViewById(R.id.edit_login_password);
        btnLogin = findViewById(R.id.btn_login);

        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {
            signIn(editId.getText().toString(), editPassword.getText().toString());
        });
    }

    private void signIn(String email, String password) {
        try {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = auth.getCurrentUser();
                    updateUser(user);
                    Toast.makeText(getApplicationContext(), "로그인 되었습니다", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                }
            }).addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "로그인 실패: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUser(FirebaseUser currentUser) {
        this.currentUser = currentUser;
    }
}
