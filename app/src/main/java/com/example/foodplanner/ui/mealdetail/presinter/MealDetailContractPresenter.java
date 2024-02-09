package com.example.foodplanner.ui.mealdetail.presinter;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.data.MealPlane;

public interface MealDetailContractPresenter {
    void setMealData(Meal meal);
    public void addProductToFavorite(Meal meal);
    public void addMealPlane(MealPlane mealPlane);
    public void addMealToFavRemote(Meal meal);
    public void addMealPlaneRemote (MealPlane meal);


}
