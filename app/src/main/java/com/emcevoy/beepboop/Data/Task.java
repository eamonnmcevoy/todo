package com.emcevoy.beepboop.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 13/06/2017.
 */

public class Task {
    private String title;
    private String when;
    private Date date;
    private List<Subtask> subtasks;

    public Task(String title, Date date) {
        this.title = title;
        this.when = when;
        this.date = date;
        subtasks = new ArrayList<>();
    }

    public String getTitle() {
        return this.title;
    }

    public String getWhen() {
        return this.when;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) { this.date = date; }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }
}
