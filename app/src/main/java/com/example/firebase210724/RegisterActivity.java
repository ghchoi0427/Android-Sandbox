package com.example.firebase210724;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth auth;

    EditText editRegisterId;
    EditText editRegisterPassword;
    Button btnRegisterAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editRegisterId = findViewById(R.id.edit_register_id);
        editRegisterPassword = findViewById(R.id.edit_register_password);
        btnRegisterAccount = findViewById(R.id.btn_register_account);

        auth = FirebaseAuth.getInstance();
        btnRegisterAccount.setOnClickListener(v -> createAccount(editRegisterId.getText().toString().trim(), editRegisterPassword.getText().toString().trim()));
    }

    private void createAccount(String email, String password) {
        try {
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Register succeed", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, LoginActivity.class));
                        }
                    }).addOnFailureListener(e -> Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
