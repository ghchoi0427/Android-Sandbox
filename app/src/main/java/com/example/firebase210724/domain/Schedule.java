package com.example.firebase210724.domain;

import com.google.firebase.Timestamp;

import java.util.List;

public class Schedule {

    Timestamp date;
    List<String> userIds;
    List<String> todo;

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<String> getTodo() {
        return todo;
    }

    public void setTodo(List<String> todo) {
        this.todo = todo;
    }
}
