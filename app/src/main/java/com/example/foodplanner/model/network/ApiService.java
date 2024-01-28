package com.example.foodplanner.model.network;

import com.example.foodplanner.model.response.CategoryResponse;
import com.example.foodplanner.model.response.CuisineResponse;
import com.example.foodplanner.model.response.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("categories.php")
    Call<CategoryResponse> getCategories();
    @GET("list.php?a=list")
    Call<CuisineResponse> getCuisine();
    @GET("random.php")
    Call<MealResponse> getMeals();


}

