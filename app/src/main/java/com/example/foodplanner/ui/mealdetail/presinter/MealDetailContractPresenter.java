package com.example.foodplanner.ui.mealdetail.presinter;

import com.example.foodplanner.model.data.Meal;

public interface MealDetailContractPresenter {
    void setMealData(Meal meal);
    public void addProductToFavorite(Meal meal);


}
