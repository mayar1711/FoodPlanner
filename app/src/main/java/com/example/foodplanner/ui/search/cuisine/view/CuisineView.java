package com.example.foodplanner.ui.search.cuisine.view;

import com.example.foodplanner.model.data.Cuisine;

import java.util.List;

public interface CuisineView {
    void showCuisine(List<Cuisine> cuisines);
    void showError(String error);
}
