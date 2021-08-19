package com.example.firebase210724.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase210724.R;
import com.example.firebase210724.domain.Board;

import java.util.ArrayList;
import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    List<Board> boardList = new ArrayList<>();

    public List<Board> getBoardList() {
        return boardList;
    }

    public List<Board> getBoards() {
        return boardList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_board, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.ViewHolder viewHolder, int position) {
        Board board = boardList.get(position);
        viewHolder.setItem(board);
    }

    @Override
    public int getItemCount() {
        return boardList.size();
    }

    public void addBoard(Board board) {
        boardList.add(board);
    }

    public void addBoards(List<Board> boardList) {
        this.boardList.addAll(boardList);
        Log.d("tester", "addboards" + this.boardList.size());
    }

    public void clearItem() {
        boardList.clear();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;

        public ViewHolder(View boardView) {
            super(boardView);
            title = boardView.findViewById(R.id.txt_board_title);

            boardView.setOnClickListener(view -> {
                //boardView.getContext().startActivity(new Intent(boardView.getContext(), BoardActivity.class));
            });
        }

        public void setItem(Board board) {
            try {
                title.setText(board.getTitle());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
