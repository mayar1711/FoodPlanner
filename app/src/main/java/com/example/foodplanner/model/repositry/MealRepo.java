package com.example.foodplanner.model.repositry;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.data.MealPlane;
import com.example.foodplanner.model.response.MealResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface MealRepo {
    public Single<MealResponse> getAllProducts();
    Completable insertProductToFavorite(Meal meal);
    void deleteFavoriteProduct(Meal meal);
    Flowable<List<Meal>> getFavProducts();
    Completable insertMealToPlane(MealPlane meal);
    void deleteMealPlane(MealPlane meal);
    Flowable<List<MealPlane>> getPlaneMeal();

}
