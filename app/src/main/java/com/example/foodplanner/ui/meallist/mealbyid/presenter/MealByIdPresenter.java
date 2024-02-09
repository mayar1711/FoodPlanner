package com.example.foodplanner.ui.meallist.mealbyid.presenter;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.data.MealPlane;

public interface MealByIdPresenter {
        void getMealById(String id);
        public void addProductToFav(Meal meal);
        public void addMealPlane(MealPlane mealPlane);

}
