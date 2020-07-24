package com.monwareclinical.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.monwareclinical.R;
import com.monwareclinical.model.Hour;

import java.util.ArrayList;
import java.util.List;

public class HoursAdapter extends RecyclerView.Adapter<HoursAdapter.ViewHolder> {

    Context context;
    List<Hour> hours;
    SelectIconListener listener;

    public HoursAdapter(Context context, SelectIconListener listener) {
        this.context = context;
        hours = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hour, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.txtHour.setText(hours.get(position).getHour());

        String txtAvailable;
        switch (hours.get(position).getState()) {
            case Hour.TOOK:
                txtAvailable = "Ocupado";
                holder.txtIsAvailable.setTextColor(context.getColor(R.color.colorUnavailable));
                break;
            case Hour.AVAILABLE:
                txtAvailable = "Disponible";
                holder.txtIsAvailable.setTextColor(context.getColor(R.color.colorAvailable));
                break;
            case Hour.SELECTED:
                txtAvailable = "Selecionado";
                holder.txtIsAvailable.setTextColor(context.getColor(R.color.colorSelected));
                break;
            default:
                txtAvailable = "";
                break;
        }
        holder.txtIsAvailable.setText(txtAvailable);
    }

    @Override
    public int getItemCount() {
        return hours.size();
    }

    public void setHours(List<Hour> hours) {
        this.hours = hours;
        notifyDataSetChanged();
    }

    public Hour getHourByPosition(int position) {
        return hours.get(position);
    }

    public void selectHour(int position) {
        hours.get(position).setState(Hour.SELECTED);
        notifyItemChanged(position);
    }

    public void cleanSelectedHours() {
        for (Hour h : hours)
            if (h.getState() == Hour.SELECTED)
                h.setState(Hour.AVAILABLE);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        TextView txtHour;
        TextView txtIsAvailable;
        SelectIconListener listener;

        public ViewHolder(View itemView, SelectIconListener onIconClick) {
            super(itemView);

            txtHour = itemView.findViewById(R.id.txtHour);
            txtIsAvailable = itemView.findViewById(R.id.txtIsAvailable);
            listener = onIconClick;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onHourSelected(getAdapterPosition());
        }
    }

    public interface SelectIconListener {
        void onHourSelected(int position);
    }
}