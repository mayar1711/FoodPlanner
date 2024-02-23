package com.example.foodplanner.model.firebase.datasource;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.data.MealPlane;

import io.reactivex.rxjava3.core.Completable;

    public interface MealRemoteDatasource {
        Completable insertProductToFavorite( Meal meal);
        Completable insertMealToPlan(MealPlane meal);
        void deletePlane(MealPlane mealPlane);
        void deleteFav(Meal meal);

    }
