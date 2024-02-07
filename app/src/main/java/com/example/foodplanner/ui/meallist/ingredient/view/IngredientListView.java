package com.example.foodplanner.ui.meallist.ingredient.view;

import com.example.foodplanner.model.data.Meal;

import java.util.ArrayList;

public interface IngredientListView {
    void showIngredientMealList(ArrayList<Meal> mealPreviews);
    void showIngredientError(String error);
}
