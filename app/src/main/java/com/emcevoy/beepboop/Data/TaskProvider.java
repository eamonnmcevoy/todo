package com.emcevoy.beepboop.Data;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;

public class TaskProvider {
    List<Task> taskList;

    public TaskProvider() {
        taskList = new ArrayList<>();
        taskList.add(new Task("Taskarooy","Today"));
        taskList.add(new Task("Task-a-doodly-doo","Tomorrow"));
        taskList.add(new Task("Task shmask","Next week"));
    }

    public Completable add(Task task) {
        return Completable.fromAction(() -> taskList.add(task));
    }

    public List<Task> getAll(){
        return taskList;
    }
}
