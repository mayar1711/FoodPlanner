package com.example.foodplanner.ui.meallist.view;

import com.example.foodplanner.model.data.MealPreview;

import java.util.ArrayList;

public interface MealListView {
    void showMealList(ArrayList<MealPreview> mealPreviews);
    void showError(String error);
}
