package com.example.foodplanner.model.repositry.localrepo;

import com.example.foodplanner.model.data.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface MealLocalDatasource {
    Completable insertProductToFavorite(Meal meal);
    void deleteFavoriteProduct(Meal meal);
    Flowable<List<Meal>> getFavMeals();
}
