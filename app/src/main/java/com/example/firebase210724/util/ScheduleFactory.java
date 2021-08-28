package com.example.firebase210724.util;

import com.example.firebase210724.domain.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleFactory {

    public static Schedule createSchedule(String date, List<String> todo, List<String> userIds) {
        Schedule schedule = new Schedule();
        schedule.setDate(date);
        schedule.setTodo(todo);
        schedule.setUserIds(userIds);
        return schedule;
    }

    public static Schedule createSchedule(String date, String todo, String userId) {
        Schedule schedule = new Schedule();
        List<String> todos = new ArrayList<>();
        List<String> userIds = new ArrayList<>();
        todos.add(todo);
        userIds.add(userId);

        schedule.setDate(date);
        schedule.setTodo(todos);
        schedule.setUserIds(userIds);
        return schedule;
    }
}
