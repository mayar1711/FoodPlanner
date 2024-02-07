package com.example.foodplanner.ui.search.ingredient.view;

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
import com.example.foodplanner.model.data.Ingredient;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder>   {
     private List<Ingredient> ingredientList;
     private OnIngredientClick onIngredientClick;

    public void setOnIngredientClick(OnIngredientClick onIngredientClick) {
        this.onIngredientClick = onIngredientClick;
    }
    public void setIngredientList(List<Ingredient>ingredientList)
    {
        this.ingredientList=ingredientList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_iteam_list, parent, false);
        return new IngredientAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          Ingredient ingredient=ingredientList.get(position);
        String name = ingredientList.get(holder.getAbsoluteAdapterPosition()).getStrIngredient();
          holder.ingredientName.setText(ingredient.getStrIngredient());
        Glide.with(holder.itemView.getContext())
                .load("https://www.themealdb.com/images/ingredients/" + name + "-Small.png")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ingredientImage);
        holder.itemView.setOnClickListener(v -> {
            if (onIngredientClick != null) {
                onIngredientClick.onIngredientClick(ingredient);
            }
        });
    }

    @Override
    public int getItemCount() {
        return  ingredientList != null ?ingredientList.size() :0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView ingredientName;
        private ImageView ingredientImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName=itemView.findViewById(R.id.tv_name);
            ingredientImage=itemView.findViewById(R.id.image_ingradient);
        }
    }
}
