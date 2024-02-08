package com.example.foodplanner.ui.plane.presenter;

import com.example.foodplanner.model.data.MealPlane;
import com.example.foodplanner.ui.plane.view.PlaneView;

public interface MealPlanerPresenter {
    public void getPlane(PlaneView planeview);
    public void deletePlaneMeal(MealPlane meal);

}
