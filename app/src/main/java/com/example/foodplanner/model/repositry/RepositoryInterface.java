package com.example.foodplanner.model.repositry;

import com.example.foodplanner.model.response.CategoryResponse;
import com.example.foodplanner.model.response.CuisineResponse;
import com.example.foodplanner.model.response.IngredientResponse;
import com.example.foodplanner.model.response.MealResponse;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public interface RepositoryInterface {
    Disposable getCategories(SingleObserver<CategoryResponse> observer);
    Disposable getCuisine(SingleObserver<CuisineResponse> observer);
    Disposable getMealList(SingleObserver<MealResponse> observer);
    Disposable getIngredient(SingleObserver<IngredientResponse> observer);
    Single<MealResponse> getMealsByCategory(String category);
    Single<MealResponse> getMealsByCuisine(String cuisine);
    Single<MealResponse> getMealByIngredient(String ingredient);
    Single<MealResponse> getMealById(String id);
}
