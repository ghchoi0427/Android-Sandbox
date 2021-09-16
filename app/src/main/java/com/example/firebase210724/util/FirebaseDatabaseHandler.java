package com.example.firebase210724.util;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebase210724.adapter.BoardAdapter;
import com.example.firebase210724.domain.Board;
import com.example.firebase210724.domain.Schedule;
import com.example.firebase210724.domain.User;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Date;
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

    public String getUserId() {
        return Objects.requireNonNull(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail()).split("@")[0];
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
                try {
                    board.setUserName(Objects.requireNonNull(document.getData().get("userName")).toString());
                    board.setTitle(Objects.requireNonNull(document.getData().get("title")).toString());
                    board.setContent(Objects.requireNonNull(document.getData().get("content")).toString());
                    board.setDate((Timestamp) document.getData().get("date"));
                } catch (NullPointerException e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                }

                boardList.add(board);
            }
        }).addOnCompleteListener(task -> {
            boardAdapter.addBoards(boardList);
            boardAdapter.notifyDataSetChanged();
        }).addOnFailureListener(e -> Toast.makeText(context, "게시판 조회 실패 : " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    public void getSchedule(TextView textView, int year, int month, int dayOfMonth, String user) {
        db.collection(COLLECTION_SCHEDULE).whereEqualTo("date", new Timestamp(new Date(year, month, dayOfMonth))).whereArrayContains("userIds", user).get().addOnSuccessListener(queryDocumentSnapshots -> {
        }).addOnCompleteListener(task -> {
            for (DocumentSnapshot document : task.getResult()) {
                textView.append(document.toString());
                textView.append("\n");
            }
            Toast.makeText(textView.getContext(), "load complete", Toast.LENGTH_LONG).show();
        })
                .addOnFailureListener(e -> Toast.makeText(textView.getContext(), "fail", Toast.LENGTH_LONG).show());
    }

    public void postSchedule(Context context, Schedule schedule) {
        db.collection(COLLECTION_SCHEDULE).document(schedule.getDate() + "_" + getUserId()).set(schedule).addOnSuccessListener(unused -> Toast.makeText(context, "성공적으로 게시되었습니다.", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show());
    }

    public void sendNotification(Context context) {
        CollectionReference ref = db.collection("boards");
        ref.addSnapshotListener((value, error) -> {
            if (error != null) {
                return;
            }
            NotificationHandler notification = new NotificationHandler();
            notification.sendNotification(context, "ChannelBoard"+value, "new post has been uploaded", value.toString());
        });

    }

}
