package com.monwareclinical.model;

public class User {

    String img;
    String userID;
    String username;
    String firstName;
    String lastName;
    String email;

    public User(String img, String userID, String username, String firstName, String lastName, String email) {
        this.img = img;
        this.userID = userID;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "img='" + img + '\'' +
                ", userID='" + userID + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}