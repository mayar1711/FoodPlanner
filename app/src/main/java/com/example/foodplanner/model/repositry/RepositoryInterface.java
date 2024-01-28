package com.example.foodplanner.model.repositry;

import com.example.foodplanner.model.response.CategoryResponse;
import com.example.foodplanner.model.response.CuisineResponse;
import com.example.foodplanner.model.response.MealResponse;

import retrofit2.Callback;

public interface RepositoryInterface {
    void getCategories(Callback<CategoryResponse> callback);
    void getCuisine(Callback<CuisineResponse> callback);

    void getMealList(Callback<MealResponse> callback);

}
