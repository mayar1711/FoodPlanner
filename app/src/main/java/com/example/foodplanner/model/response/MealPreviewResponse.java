package com.example.foodplanner.model.response;

import com.example.foodplanner.model.data.Meal;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MealPreviewResponse {
    @SerializedName("meals")
    public ArrayList<Meal> mealPreviews;

}
