package com.example.firebase210724.util;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebase210724.adapter.BoardAdapter;
import com.example.firebase210724.domain.Board;
import com.example.firebase210724.domain.User;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FirebaseDatabaseHandler {

    private final FirebaseFirestore db;
    private final String COLLECTION_USER = "users";
    private final String COLLECTION_BOARD = "boards";
    private final String COLLECTION_SCHEDULE = "schedules";


    public FirebaseDatabaseHandler(FirebaseFirestore db) {
        this.db = db;
    }

    public void addUser(Context context, String name) {
        User user = new User(name);

        db.collection(COLLECTION_USER).document(user.getName()).set(user)
                .addOnSuccessListener(unused -> Toast.makeText(context, "user added", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    public void postBoard(Context context, Board board) {
        db.collection(COLLECTION_BOARD).document().set(board)
                .addOnSuccessListener(unused -> Toast.makeText(context, "성공적으로 게시되었습니다.", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show());
    }

    public void setBoardList(Context context, BoardAdapter boardAdapter) {
        List<Board> boardList = new ArrayList<>();

        db.collection(COLLECTION_BOARD).orderBy("date", Query.Direction.DESCENDING).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                Board board = new Board();
                board.setUserId(Objects.requireNonNull(document.getData().get("userId")).toString());
                board.setTitle(Objects.requireNonNull(document.getData().get("title")).toString());
                board.setContent(Objects.requireNonNull(document.getData().get("content")).toString());
                board.setDate((Timestamp) document.getData().get("date"));

                boardList.add(board);
            }
        }).addOnCompleteListener(task -> {
            boardAdapter.addBoards(boardList);
            boardAdapter.notifyDataSetChanged();
        }).addOnFailureListener(e -> Toast.makeText(context, "게시판 조회 실패 : " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    public void setSchedule() {
        //db.collection()
    }

}
