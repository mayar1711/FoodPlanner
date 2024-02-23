package com.example.foodplanner.ui.mealdetail.presinter;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.data.MealPlane;

public interface MealDetailContractPresenter {
    void setMealData(Meal meal);
    void addProductToFavorite(Meal meal);
    void addMealPlane(MealPlane mealPlane);

}
