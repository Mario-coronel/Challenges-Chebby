package com.example.admin.chebbychallenge.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.chebbychallenge.R;
import com.example.admin.chebbychallenge.data.model.ClimaCity;
import com.example.admin.chebbychallenge.data.model.localday.LocalDayResponse;

import java.util.List;

public class ClimaAdapter extends RecyclerView.Adapter<ClimaAdapter.ClimaViewHolder> {

    List<ClimaCity> climas;

    public ClimaAdapter(List<ClimaCity> climalist){
        climas = climalist;
    }


    @Override
    public ClimaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.weather_row, parent, false);
        return new ClimaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClimaViewHolder holder, int position) {
        holder.name.setText("City: "+climas.get(position).getCity());
        holder.max.setText("Max temp: "+String.valueOf(climas.get(position).getMaxTemp()).substring(0,4)+"°F");
        holder.min.setText("Min temp: "+String.valueOf(climas.get(position).getMinTemp()).substring(0,4)+"°F");
        holder.state.setText("Clima: "+climas.get(position).getState());
        Glide.with(holder.itemView.getContext()).load(climas.get(position).getIconLink()).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return climas.size();
    }

    class ClimaViewHolder extends RecyclerView.ViewHolder{

        TextView min,max,name,state;
        ImageView icon;

        public ClimaViewHolder(View itemView) {
            super(itemView);
            min = itemView.findViewById(R.id.min_temp);
            max = itemView.findViewById(R.id.max_temp);
            name = itemView.findViewById(R.id.city_name);
            state = itemView.findViewById(R.id.state);
            icon = itemView.findViewById(R.id.icon);
        }
    }

}
