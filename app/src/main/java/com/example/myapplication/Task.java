package com.example.myapplication;

import java.util.Date;

public class Task {
    private int ID;
    private String NameTask;

    public String getNameTask() {
        return NameTask;
    }

    public String getBodyTask() {
        return BodyTask;
    }

    public String getDateCompleted() {
        return DateCompleted;
    }

    private String BodyTask;
    private String DateAdded;
    private String DateCompleted;

    public Task(int Id, String nameTask, String bodyTask, String dateAdded, String dateCompleted) {
        NameTask = nameTask;
        BodyTask = bodyTask;
        DateAdded = dateAdded;
        DateCompleted = dateCompleted;
    }
}
