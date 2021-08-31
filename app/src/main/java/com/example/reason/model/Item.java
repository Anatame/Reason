package com.example.reason.model;

public class Item {
    private int id;
    private String activityName;
    private String time;
    private int interval;
    private String activitySetName;
    private int isChecked;

    public Item(){

    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getActivitySetName() {
        return activitySetName;
    }

    public void setActivitySetName(String activitySetName) {
        this.activitySetName = activitySetName;
    }

    public int getChecked() {
        return isChecked;
    }

    public void setChecked(int checked) {
        isChecked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
