package com.example.foodplanner.model.repositry.localrepo;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.data.MealPlane;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface MealLocalDatasource {
    Completable insertProductToFavorite(Meal meal);
    void deleteFavoriteProduct(Meal meal);
    Flowable<List<Meal>> getFavMeals();

    Completable insertMealToPlane(MealPlane meal);
    void deleteMealPlane(MealPlane meal);
    Flowable<List<MealPlane>> getPlaneMeals();
}
