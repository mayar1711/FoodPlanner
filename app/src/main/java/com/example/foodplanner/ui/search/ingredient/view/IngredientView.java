package com.example.foodplanner.ui.search.ingredient.view;


import com.example.foodplanner.model.data.Ingredient;

import java.util.List;

public interface IngredientView {
    void showIngrediant(List<Ingredient> ingredients);
    void showIngredientError(String error);
}
