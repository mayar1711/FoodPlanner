package com.example.foodplanner.ui.searchbyname.view;

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
import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.ui.meallist.Categorie.view.MealListAdapter;

import java.util.ArrayList;

public class SearchByNameAdapter extends RecyclerView.Adapter<SearchByNameAdapter.ViewHolder>{
    private ArrayList<Meal> mealPreviews;
    private OnMealClick click;

    public void setClick(OnMealClick click) {
        this.click = click;
    }

    public SearchByNameAdapter(){
        mealPreviews = new ArrayList<>();
    }
    public void setMealPreviews(ArrayList<Meal> mealPreviews) {
        this.mealPreviews = mealPreviews;
    }

    @NonNull
    @Override
    public SearchByNameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_iteam, parent, false);
        return new SearchByNameAdapter.ViewHolder(view);
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
            if (click != null) {
                click.onClickMeal(mealPreview);
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
            mealNameTextView = itemView.findViewById(R.id.tv_search_name);
            mealImage=itemView.findViewById(R.id.image_search);
        }
    }
    }
