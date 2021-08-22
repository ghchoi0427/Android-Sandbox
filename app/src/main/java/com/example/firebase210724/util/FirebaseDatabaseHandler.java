package com.example.firebase210724.util;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebase210724.adapter.BoardAdapter;
import com.example.firebase210724.domain.Board;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

        db.collection(COLLECTION_BOARD).document().set(board)
                .addOnSuccessListener(unused -> Toast.makeText(context, "성공적으로 게시되었습니다.", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show());
    }

    public void setBoardList(Context context, BoardAdapter boardAdapter) {
        List<Board> boardList = new ArrayList<>();

        db.collection(COLLECTION_BOARD).orderBy("date", Query.Direction.DESCENDING).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                String userId = Objects.requireNonNull(document.getData().get("userId")).toString();
                String title = Objects.requireNonNull(document.getData().get("title")).toString();
                String content = Objects.requireNonNull(document.getData().get("content")).toString();
                Timestamp date = (Timestamp) document.getData().get("date");
                boardList.add(new Board(userId, title, content, date));
            }
        }).addOnCompleteListener(task -> {
            boardAdapter.addBoards(boardList);
            boardAdapter.notifyDataSetChanged();
        }).addOnFailureListener(e -> Toast.makeText(context, "게시판 조회 실패 : " + e.getMessage(), Toast.LENGTH_SHORT).show());

    }

    public void getUserNameById(String id, TextView textView) {

        db.collection(COLLECTION_USER).document(id).get()
                .addOnFailureListener(e -> {
                    Toast.makeText(textView.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                })
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        textView.setText(Objects.requireNonNull(documentSnapshot.get("name")).toString());
                    } else {
                        textView.setText("unknown user");
                    }
                });
    }

}
