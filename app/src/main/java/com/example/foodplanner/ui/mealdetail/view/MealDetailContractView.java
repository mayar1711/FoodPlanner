package com.example.foodplanner.ui.mealdetail.view;

import com.example.foodplanner.model.data.Meal;

public interface MealDetailContractView {
    void displayMealDetails(Meal meal);
    void displayError(String message);

    public void addProductToFav(Meal meal);
    void onInsertSuccess();
    void onInsertError(String errorMessage);
}
