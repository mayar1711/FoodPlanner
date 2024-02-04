package com.example.foodplanner.ui.favorite.view;

import com.example.foodplanner.model.data.Meal;

import java.util.List;

public interface FavMealView {
    public void deleteFavProduct(Meal meal);
    void onGetAllFavoriteProducts(List<Meal> favoriteMeal);
    void onGetAllFavoriteProductsError(String errorMessage);
}
