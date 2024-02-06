package com.example.foodplanner.model.response;

import com.example.foodplanner.model.data.Cuisine;
import com.example.foodplanner.model.data.Ingredient;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientResponse {
    @SerializedName("meals")
    private List<Ingredient> ingredients;
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
