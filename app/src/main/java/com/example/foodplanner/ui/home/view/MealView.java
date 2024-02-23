package com.example.foodplanner.ui.home.view;

import com.example.foodplanner.model.data.Meal;

import java.util.List;

public interface MealView {
    void displayMeals(List<Meal> meals);
    void displayError(String message);
}
