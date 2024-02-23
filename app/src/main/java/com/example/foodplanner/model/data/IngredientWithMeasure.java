package com.example.foodplanner.model.data;

public class IngredientWithMeasure {
    private String ingredientName;
    private String ingredientMeasure;

    public IngredientWithMeasure(String ingredientName, String ingredientMeasure) {
        this.ingredientName = ingredientName;
        this.ingredientMeasure = ingredientMeasure;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public String getIngredientMeasure() {
        return ingredientMeasure;
    }
}
