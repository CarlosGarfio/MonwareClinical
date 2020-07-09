package com.monwareclinical.util;

import android.content.Context;

import com.monwareclinical.model.Event;
import com.monwareclinical.model.Place;
import com.monwareclinical.model.User;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    static Constants instance;

    Context context;

    User user;

    List<Event> myEvents;
    List<Place> places;

    Constants(Context context) {
        this.context = context;

        places = new ArrayList<>();
        places.add(new Place(
                "Promedic",
                "Calle Abolicion de la Exclavitud",
                "Chihuahua",
                "Chihuahua",
                "6425",
                "6144038180"
        ));
        places.add(new Place(
                "Garfio's home",
                "Calle Villa de Antares",
                "Chihuahua",
                "Chihuahua",
                "9728",
                "4987744"
        ));

        myEvents = new ArrayList<>();

        myEvents.add(new Event(
                "Dentista",
                "18/07/2020 11:00",
                places.get(0)
        ));

        myEvents.add(new Event(
                "Gameing",
                "19/07/2020 22:00",
                places.get(1)
        ));
    }

    public static synchronized Constants getInstance(Context context) {
        if (instance == null)
            instance = new Constants(context);
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public List<Event> getMyEvents() {
        return myEvents;
    }
}