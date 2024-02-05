package com.example.foodplanner.model.repositry;

import com.example.foodplanner.model.response.CategoryResponse;
import com.example.foodplanner.model.response.CuisineResponse;
import com.example.foodplanner.model.response.MealPreviewResponse;
import com.example.foodplanner.model.response.MealResponse;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.Callback;

public interface RepositoryInterface {
    Disposable getCategories(io.reactivex.rxjava3.core.SingleObserver<CategoryResponse> observer);

    Disposable getCuisine(io.reactivex.rxjava3.core.SingleObserver<CuisineResponse> observer);

    Disposable getMealList(io.reactivex.rxjava3.core.SingleObserver<MealResponse> observer);
    Single<MealPreviewResponse> getMealsByCategory(String category);


}
