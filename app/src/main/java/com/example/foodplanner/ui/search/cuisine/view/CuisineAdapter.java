package com.example.foodplanner.ui.search.cuisine.view;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.foodplanner.R;
import com.example.foodplanner.model.data.Cuisine;

import java.util.List;

public class CuisineAdapter  extends RecyclerView.Adapter<CuisineAdapter.ViewHolder>{
    private List<Cuisine> cuisineList;
    private OnCuisineClicked cuisineClicked;

    public void setCuisineClicked(OnCuisineClicked cuisineClicked) {
        this.cuisineClicked = cuisineClicked;
    }

    public void setCuisineList(List<Cuisine> cuisineList)
    {
        this.cuisineList=cuisineList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cuisine_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cuisine cuisine=cuisineList.get(position);
        holder.cuisineName.setText(cuisine.getStrArea());
        holder.itemView.setOnClickListener(v -> {
            if (cuisineClicked != null) {
                cuisineClicked.onCuisineClicked(cuisine);
            }
        });
    }


    @Override
    public int getItemCount() {
        return cuisineList!=null? cuisineList.size():0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView cuisineName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cuisineName=itemView.findViewById(R.id.tv_cuisine_name);
        }
    }
}
