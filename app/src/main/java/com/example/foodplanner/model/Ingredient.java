package com.example.foodplanner.model;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName("meals")
    public ArrayList<Ingredient> ingredients;

}
