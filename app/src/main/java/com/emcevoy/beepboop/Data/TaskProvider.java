package com.emcevoy.beepboop.Data;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.functions.Action;

public class TaskProvider {
    List<Task> taskList;

    public TaskProvider() {
        taskList = new ArrayList<>();
        taskList.add(new Task("Taskarooy",null));
        taskList.add(new Task("Task-a-doodly-doo",null));
        taskList.add(new Task("Task shmask",null));
    }

    public Completable add(final Task task) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                taskList.add(task);
            }
        });
    }

    public Completable createNewTask(final String text) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Parser parser = new Parser();

                List<DateGroup> groups = parser.parse(text);

                Date date = null;
                for(DateGroup group:groups) {
                    List<Date> dates = group.getDates();
                    int line = group.getLine();
                    int column = group.getPosition();
                    String matchingValue = group.getText();
                    String syntaxTree = group.getSyntaxTree().toStringTree();
                    Map parseMap = group.getParseLocations();
                    boolean isRecurreing = group.isRecurring();
                    Date recursUntil = group.getRecursUntil();

                    if(!dates.isEmpty()) date = dates.get(0);
                }
                Task task = new Task(text, date);
                taskList.add(task);
            }
        });
    }

    public List<Task> getAll(){
        return taskList;
    }
}
