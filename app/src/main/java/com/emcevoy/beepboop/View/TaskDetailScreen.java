package com.emcevoy.beepboop.View;

import android.content.Context;

import com.emcevoy.beepboop.Data.Task;
import com.wealthfront.magellan.Screen;

public class TaskDetailScreen extends Screen<TaskDetailView> {
    Task task;

    public TaskDetailScreen(Task task) {
        this.task = task;
    }

    @Override
    protected TaskDetailView createView(Context context) {
        return new TaskDetailView(context, task);
    }
}
