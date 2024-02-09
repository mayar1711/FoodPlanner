package com.example.foodplanner.ui.meallist.mealbyid.view;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.data.MealPlane;

public interface MealByIdView {
    void showMealById(Meal meal); // Update method signature
    void showListError(String error);
    public void addProductToFav(Meal meal);
    public void addMealPlane(MealPlane mealPlane);
    void onInsertSuccess();
    void onInsertError(String errorMessage);
}
