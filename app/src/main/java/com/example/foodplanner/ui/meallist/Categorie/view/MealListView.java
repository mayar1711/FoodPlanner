package com.example.foodplanner.ui.meallist.Categorie.view;

import com.example.foodplanner.model.data.Meal;

import java.util.ArrayList;

public interface MealListView {
    void showMealList(ArrayList<Meal> mealPreviews);
    void showError(String error);
}
