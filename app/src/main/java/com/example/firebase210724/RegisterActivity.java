package com.example.firebase210724;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase210724.util.FirebaseDatabaseHandler;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth auth;

    EditText editRegisterId;
    EditText editRegisterPassword;
    Button btnRegisterAccount;
    private final String EMAIL_SUFFIX = "@gmail.com";
    FirebaseDatabaseHandler firebaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editRegisterId = findViewById(R.id.edit_register_id);
        editRegisterPassword = findViewById(R.id.edit_register_password);
        btnRegisterAccount = findViewById(R.id.btn_register_account);
        firebaseHandler = new FirebaseDatabaseHandler(FirebaseFirestore.getInstance());


        auth = FirebaseAuth.getInstance();
        btnRegisterAccount.setOnClickListener(v -> createAccount(editRegisterId.getText().toString().trim(), editRegisterPassword.getText().toString().trim()));
    }

    private void createAccount(String name, String password) {
        try {
            auth.createUserWithEmailAndPassword(name + EMAIL_SUFFIX, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            firebaseHandler.addUser(getApplicationContext(), name);
                            Toast.makeText(getApplicationContext(), "Register succeed", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, LoginActivity.class));
                        }
                    }).addOnFailureListener(e -> Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
