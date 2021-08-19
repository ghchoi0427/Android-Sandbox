package com.example.firebase210724.util;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FirebaseDatabaseHandler {

    private final FirebaseFirestore db;
    private final String COLLECTION_USER = "users";
    private final String COLLECTION_BOARD = "boards";


    public FirebaseDatabaseHandler(FirebaseFirestore db) {
        this.db = db;
    }

    public void addUser(Context context, String name, String ID) {
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);

        db.collection(COLLECTION_USER).document(ID).set(user)
                .addOnSuccessListener(unused -> Toast.makeText(context, "user added", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    public void post(Context context, String title, String content, String userId) {
        Map<String, Object> board = new HashMap<>();
        board.put("content", content);
        board.put("title", title);
        board.put("date", new Timestamp(new Date()));
        board.put("userId", userId);


        db.collection(COLLECTION_BOARD)
                .add(board)
                .addOnSuccessListener(documentReference -> Toast.makeText(context, "성공적으로 게시되었습니다.", Toast.LENGTH_SHORT).show()).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private int getLastIndex() {
        return Integer.parseInt(db.collection(COLLECTION_BOARD).document().getId());
    }

}
