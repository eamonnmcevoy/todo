package com.emcevoy.beepboop.Data;

public class Subtask {
    private String title;
    private boolean done;

    public Subtask(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean getDone() {
        return done;
    }
}
