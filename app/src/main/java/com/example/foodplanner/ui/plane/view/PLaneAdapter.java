package com.example.foodplanner.ui.plane.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.model.data.MealPlane;

import java.util.List;

public class PLaneAdapter extends RecyclerView.Adapter<PLaneAdapter.ViewHolder> {

    private List<MealPlane> meals;
    public OnDeleteClickListener onDeleteClickListener;
    public PLaneAdapter(List<MealPlane> meals)
    {
        this.meals=meals;
    }
    public void changeData(List<MealPlane> meals) {
        this.meals = meals;

    }
    public List<MealPlane> getMeals() {
        return meals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.plane_iteam,parent,false);
       PLaneAdapter.ViewHolder viewHolder = new PLaneAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealPlane meal=meals.get(position);
        holder.titleTextView.setText(meal.getStrMeal());
        holder.date.setText(meal.getDate());
        Glide.with(holder.imageView.getContext()).load(meal.getStrMealThumb()).into(holder.imageView);
        holder.deleteBtn.setOnClickListener(view -> {
            onDeleteClickListener.onItemClickListener(meal);
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView titleTextView;
        public ImageView deleteBtn;
        public TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView_item);
            titleTextView = itemView.findViewById(R.id.meal_title_item);
            date=itemView.findViewById(R.id.date);
            deleteBtn = itemView.findViewById(R.id.delete_plane_meal);
        }
    }
    public interface OnDeleteClickListener{
        void onItemClickListener(MealPlane meal);
    }
}