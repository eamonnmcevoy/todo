package com.emcevoy.beepboop.Data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Task {
    private String title;
    private Date date;
    private List<Subtask> subtasks;

    Task(String title, Date date) {
        this.title = title;
        this.date = date;
        subtasks = new ArrayList<>();
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDateTime(Date date) { this.date = date; }

    public void setDatePart(Date date) {
        if(this.date == null) {
            this.date = date;
            return;
        }
        Calendar newCal = Calendar.getInstance(); // locale-specific
        newCal.setTime(date);

        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(this.date);
        cal.set(Calendar.YEAR, newCal.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, newCal.get(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, newCal.get(Calendar.DAY_OF_MONTH));

        this.date = cal.getTime();
    }

    public void setTimePart(Date date) {
        if(this.date == null) {
            this.date = date;
            return;
        }
        Calendar newCal = Calendar.getInstance();
        newCal.setTime(date);

        Calendar cal = Calendar.getInstance();
        cal.setTime(this.date);
        cal.set(Calendar.HOUR_OF_DAY, newCal.get(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, newCal.get(Calendar.MINUTE));
        this.date = cal.getTime();
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }
}
