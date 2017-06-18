package com.emcevoy.beepboop.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 13/06/2017.
 */

public class Task {
    private String title;
    private String when;
    private List<Subtask> subtasks;

    public Task(String title, String when) {
        this.title = title;
        this.when = when;
        subtasks = new ArrayList<>();
    }

    public String getTitle() {
        return this.title;
    }

    public String getWhen() {
        return this.when;
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }
}
