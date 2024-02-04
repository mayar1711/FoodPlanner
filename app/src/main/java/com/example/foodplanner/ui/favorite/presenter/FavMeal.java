package com.example.foodplanner.ui.favorite.presenter;

import com.example.foodplanner.model.data.Meal;

public interface FavMeal {
    public void getProducts();
    public void deleteFavoriteProduct(Meal meal);
}
