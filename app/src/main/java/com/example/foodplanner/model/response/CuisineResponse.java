package com.example.foodplanner.model.response;

import com.example.foodplanner.model.data.Cuisine;
import java.util.List;

public class CuisineResponse {
    private List<Cuisine> meals;  // Use "meals" instead of "cuisines"

    public List<Cuisine> getMeals() {
        return meals;
    }
}
