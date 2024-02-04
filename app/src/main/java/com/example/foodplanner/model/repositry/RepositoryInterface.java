package com.example.foodplanner.model.repositry;

import com.example.foodplanner.model.response.CategoryResponse;
import com.example.foodplanner.model.response.CuisineResponse;
import com.example.foodplanner.model.response.MealResponse;

import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.Callback;

public interface RepositoryInterface {
    Disposable getCategories(io.reactivex.rxjava3.core.SingleObserver<CategoryResponse> observer);

    void getCuisine(Callback<CuisineResponse> callback);

    void getMealList(Callback<MealResponse> callback);

}
