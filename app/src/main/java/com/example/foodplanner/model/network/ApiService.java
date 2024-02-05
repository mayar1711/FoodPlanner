package com.example.foodplanner.model.network;

import com.example.foodplanner.model.response.CategoryResponse;
import com.example.foodplanner.model.response.CuisineResponse;
import com.example.foodplanner.model.response.MealPreviewResponse;
import com.example.foodplanner.model.response.MealResponse;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("categories.php")
    Single<CategoryResponse> getCategories();
    @GET("list.php?a=list")
    Single<CuisineResponse> getCuisine();
    @GET("random.php")
    Call<MealResponse> getMeals();
    @GET("filter.php")
    public Single<MealPreviewResponse> getMealsByIngredient(@Query("i") String ingredient);
    @GET("filter.php")
    public Single<MealPreviewResponse> getMealsByCategory(@Query("c") String category);
    @GET("filter.php")
    public Single<MealPreviewResponse> getMealsByCuisine(@Query("a") String cuisine);
}