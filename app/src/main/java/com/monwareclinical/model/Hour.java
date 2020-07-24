package com.monwareclinical.model;

public class Hour {

    public static final int TOOK = 0;
    public static final int AVAILABLE = 1;
    public static final int SELECTED = 2;

    String hour;
    int state;

    public Hour() {
    }

    public Hour(String hour, int state) {
        this.hour = hour;
        this.state = state;
    }

    public String getHour() {
        return hour;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Hour{" +
                "hour='" + hour + '\'' +
                ", state=" + state +
                '}';
    }
}
