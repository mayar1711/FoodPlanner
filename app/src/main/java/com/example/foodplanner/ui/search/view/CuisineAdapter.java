package com.example.foodplanner.ui.search.view;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.foodplanner.R;
import com.example.foodplanner.model.data.Category;
import com.example.foodplanner.model.data.Cuisine;
import com.example.foodplanner.ui.home.view.CategoryAdapter;

import java.util.List;

public class CuisineAdapter  extends RecyclerView.Adapter<CuisineAdapter.ViewHolder>{
    private List<Cuisine> cuisineList;
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
