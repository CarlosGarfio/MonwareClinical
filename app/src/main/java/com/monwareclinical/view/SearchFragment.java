package com.monwareclinical.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monwareclinical.R;
import com.monwareclinical.adapter.PlacesAdapter;
import com.monwareclinical.model.Place;
import com.monwareclinical.util.Constants;

import java.util.List;

public class SearchFragment extends Fragment {

    View root;
    Context context;

    RecyclerView placesRecyclerView;
    PlacesAdapter placesAdapter;

    List<Place> places;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.search_fragment, container, false);

        context = root.getContext();

        places = Constants.getInstance(context).getPlaces();

        placesRecyclerView = root.findViewById(R.id.placesRecyclerView);
        placesAdapter = new PlacesAdapter(context, places);
        placesRecyclerView.setAdapter(placesAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        placesRecyclerView.setLayoutManager(layoutManager);

        return root;
    }
}