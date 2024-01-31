package com.example.foodplanner.ui.mealdetail.presinter;

import com.example.foodplanner.model.data.Meal;

public interface MealDetailContractView {
    void displayMealDetails(Meal meal);
    void displayError(String message);
}
