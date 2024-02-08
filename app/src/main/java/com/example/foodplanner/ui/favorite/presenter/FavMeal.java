package com.example.foodplanner.ui.favorite.presenter;

import android.content.Context;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.ui.favorite.view.FavMealView;

public interface FavMeal {
    public void getProducts(FavMealView view);
    public void deleteFavoriteProduct(Meal meal);
}
