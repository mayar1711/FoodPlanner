package com.example.foodplanner.ui.mealdetail.view;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.data.MealPlane;

public interface MealDetailContractView {
    void displayMealDetails(Meal meal);
    void displayError(String message);
    public void addProductToFav(Meal meal);
    public void addMealPlane(MealPlane mealPlane);
    void onInsertSuccess();
    void onInsertError(String errorMessage);
}
