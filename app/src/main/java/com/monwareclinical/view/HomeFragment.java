package com.monwareclinical.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monwareclinical.R;
import com.monwareclinical.adapter.MyEventAdapter;
import com.monwareclinical.model.Event;
import com.monwareclinical.model.Place;
import com.monwareclinical.util.Constants;

import java.util.List;

public class HomeFragment extends Fragment {

    View root;
    Context context;

    ViewPager myEventsViewPager;
    MyEventAdapter eventAdapter;

    List<Event> myEvents;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.home_fragment, container, false);
        context = root.getContext();

        myEventsViewPager = root.findViewById(R.id.viewPagerMyEvents);

        myEvents = Constants.getInstance(context).getMyEvents();

        eventAdapter = new MyEventAdapter(myEvents, context);
        myEventsViewPager.setAdapter(eventAdapter);

        return root;
    }
}