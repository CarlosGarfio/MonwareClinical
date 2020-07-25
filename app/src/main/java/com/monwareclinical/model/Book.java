package com.monwareclinical.model;

public class Book {

    public static final int TOOK = 0;
    public static final int AVAILABLE = 1;
    public static final int SELECTED = 2;

    String userID;
    String title;
    String date;
    String hour;
    int state;

    public Book() {
    }

    public Book(String userID, String title, String date, String hour, int state) {
        this.userID = userID;
        this.title = title;
        this.date = date;
        this.hour = hour;
        this.state = state;
    }

    public String getUserID() {
        return userID;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
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
        return "Book{" +
                "userID='" + userID + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", hour='" + hour + '\'' +
                ", state=" + state +
                '}';
    }
}