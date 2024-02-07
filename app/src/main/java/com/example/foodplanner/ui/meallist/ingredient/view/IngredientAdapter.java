package com.example.foodplanner.ui.meallist.ingredient.view;

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
import com.example.foodplanner.ui.meallist.cuisinemeal.view.CuisineAdapter;
import com.example.foodplanner.ui.meallist.cuisinemeal.view.OnItemClicked;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    private ArrayList<Meal> mealPreviews;
    private OnIngredientItemClick clicked;

    public void setClicked(OnIngredientItemClick clicked) {
        this.clicked = clicked;
    }
    public IngredientAdapter(){
        mealPreviews = new ArrayList<>();

    }
    public void setMealPreviews(ArrayList<Meal> mealPreviews) {
        this.mealPreviews = mealPreviews;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cuisine_item_list, parent, false);
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
            if (clicked != null) {
                clicked.onClickIngredient(mealPreview);
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
            mealNameTextView=itemView.findViewById(R.id.tv_cuisine_name);
            mealImage=itemView.findViewById(R.id.cuisine_image);
        }
    }

}
