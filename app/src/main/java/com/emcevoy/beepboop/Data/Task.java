package com.emcevoy.beepboop.Data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 13/06/2017.
 */

public class Task {
    private String title;
    private Date date;
    private List<Subtask> subtasks;

    public Task(String title, Date date) {
        this.title = title;
        this.date = date;
        subtasks = new ArrayList<>();
    }

    public String getTitle() {
        return this.title;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) { this.date = date; }

    public void setDatePart(Date date) {
        Calendar newCal = Calendar.getInstance(); // locale-specific
        newCal.setTime(date);

        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(this.date);
        cal.set(Calendar.YEAR, newCal.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, newCal.get(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, newCal.get(Calendar.DAY_OF_MONTH));

        this.date = cal.getTime();
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }
}
