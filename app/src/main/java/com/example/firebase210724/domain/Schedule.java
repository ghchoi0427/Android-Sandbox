package com.example.firebase210724.domain;

import java.util.List;

public class Schedule {

    String date;
    List<String> userIds;
    List<String> todo;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public void addUser(String userId) {
        userIds.add(userId);
    }
}
