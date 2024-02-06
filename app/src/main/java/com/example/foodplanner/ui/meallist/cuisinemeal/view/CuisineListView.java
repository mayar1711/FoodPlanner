package com.example.foodplanner.ui.meallist.cuisinemeal.view;

import com.example.foodplanner.model.data.Meal;

import java.util.ArrayList;

public interface CuisineListView {
    void showResult(ArrayList<Meal> meals);
    void showError(String error);

}
