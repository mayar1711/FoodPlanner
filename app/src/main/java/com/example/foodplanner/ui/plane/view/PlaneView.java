package com.example.foodplanner.ui.plane.view;

import com.example.foodplanner.model.data.MealPlane;

import java.util.List;

public interface PlaneView {
    public void deletePlaneMeal(MealPlane meal);
    void onGetAllPlaneMeal(List<MealPlane> favoriteMeal);
    void onGetAllPlaneMealError(String errorMessage);

}
