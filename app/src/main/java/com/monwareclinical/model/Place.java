package com.monwareclinical.model;

import java.io.Serializable;

public class Place implements Serializable {

    String name;
    String streetAddress;
    String city;
    String state;
    String extNumber;
    String phoneNumber;


    public Place(String name, String streetAddress, String city, String state, String extNumber, String phoneNumber) {
        this.name = name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.extNumber = extNumber;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getExtNumber() {
        return extNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", extNumber='" + extNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}