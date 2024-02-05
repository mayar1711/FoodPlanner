package com.example.foodplanner.ui.meallist.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.foodplanner.R;
import com.example.foodplanner.model.data.Meal;

import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.ViewHolder> {
    private ArrayList<Meal> mealPreviews;
    private OnItemClickListener clickListener;
    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public MealListAdapter() {
        mealPreviews = new ArrayList<>();
    }

    public void setMealPreviews(ArrayList<Meal> mealPreviews) {
        this.mealPreviews = mealPreviews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal mealPreview = mealPreviews.get(position);
        holder.mealNameTextView.setText(mealPreview.getStrMeal());
        Glide.with(holder.itemView.getContext())
                .load(mealPreview.getStrMealThumb())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mealImage);
        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onClickCategory(mealPreview);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealPreviews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mealNameTextView;
        ImageView mealImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealNameTextView = itemView.findViewById(R.id.textView_meal_title_item_main);
            mealImage=itemView.findViewById(R.id.imageView_item_main);
        }
    }
}
