package com.monwareclinical.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.monwareclinical.R;
import com.monwareclinical.model.Place;
import com.monwareclinical.view.PlaceActivity;

import java.util.List;


public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.MyViewHolder> {

    public static final String EXTRA_PLACE="com.monwareclinical.adapter.EXTRA_PLACE";

    Context context;
    List<Place> places;

    public PlacesAdapter(Context context, List<Place> places) {
        this.context = context;
        this.places = places;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_place, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int position) {
        System.out.println(places.get(position));
        myViewHolder.imgBannerPlace.setImageDrawable(context.getDrawable(R.drawable.forgotten_password));
        myViewHolder.txtPlaceTitle.setText(places.get(position).getName());
        myViewHolder.btnMoreInformation.setOnClickListener(view -> context.startActivity(new Intent(context, PlaceActivity.class).putExtra(EXTRA_PLACE, places.get(position))));
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgBannerPlace;
        TextView txtPlaceTitle;
        Button btnMoreInformation;

        public MyViewHolder(View itemView) {
            super(itemView);

            imgBannerPlace = itemView.findViewById(R.id.imgBannerPlace);
            txtPlaceTitle = itemView.findViewById(R.id.txtTitlePlace);
            btnMoreInformation = itemView.findViewById(R.id.btnMoreInformation);
        }
    }
}