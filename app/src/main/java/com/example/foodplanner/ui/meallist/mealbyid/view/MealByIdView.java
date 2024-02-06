package com.example.foodplanner.ui.meallist.mealbyid.view;

import com.example.foodplanner.model.data.Meal;

public interface MealByIdView {
    void showMealById(Meal meal); // Update method signature
    void showListError(String error);
}
