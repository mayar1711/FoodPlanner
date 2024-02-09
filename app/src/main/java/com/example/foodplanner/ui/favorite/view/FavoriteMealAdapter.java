package com.example.foodplanner.ui.favorite.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.model.data.Meal;

import java.util.List;

public class FavoriteMealAdapter extends RecyclerView.Adapter<FavoriteMealAdapter.FavProducctViewHolder> {
    private List<Meal> meals;
    public OnDeleteClickListener onDeleteClickListener;
    private OnClickListener listener;
    public FavoriteMealAdapter(List<Meal> meals)
    {
        this.meals=meals;
    }
    public void changeData(List<Meal> meals) {
        this.meals = meals;

    }
    public List<Meal> getMeals() {
        return meals;
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public FavProducctViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fav_item,parent,false);
        FavProducctViewHolder viewHolder = new FavProducctViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull FavProducctViewHolder holder, int position) {
       Meal meal=meals.get(position);
       holder.titleTextView.setText(meal.getStrMeal());
        Glide.with(holder.imageView.getContext()).load(meal.getStrMealThumb()).into(holder.imageView);
        holder.deleteBtn.setOnClickListener(view -> {
            onDeleteClickListener.onItemClickListener(meal);
        });
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClickMeal(meal);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.i("TAG", "getItemCount: "+meals.size());
        return meals.size();
    }

    public class FavProducctViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView titleTextView;
        public ImageView deleteBtn;

        public FavProducctViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView_item_main);
            titleTextView = itemView.findViewById(R.id.textView_meal_title_item_main);
            deleteBtn=itemView.findViewById(R.id.delteMeal);
        }
    }
    public interface OnDeleteClickListener{
        void onItemClickListener(Meal meal);
    }
}
