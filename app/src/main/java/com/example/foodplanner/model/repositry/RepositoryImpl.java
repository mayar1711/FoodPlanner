package com.example.foodplanner.model.repositry;

import android.util.Log;
import com.example.foodplanner.model.network.ApiService;
import com.example.foodplanner.model.response.CategoryResponse;
import com.example.foodplanner.model.response.CuisineResponse;
import com.example.foodplanner.model.response.MealResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryImpl implements RepositoryInterface {
    private final ApiService apiService;
    private static final String TAG ="repo";
    public RepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getCategories(Callback<CategoryResponse> callback) {
        Call<CategoryResponse> call = apiService.getCategories();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callback.onResponse(call, Response.success(response.body()));
                    }
                } else {
                    callback.onFailure(call, new Throwable("Failed to fetch categories"));
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    @Override
    public void getCuisine(Callback<CuisineResponse> callback) {
        Call<CuisineResponse> call = apiService.getCuisine();
        call.enqueue(new Callback<CuisineResponse>() {
            @Override
            public void onResponse(Call<CuisineResponse> call, Response<CuisineResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callback.onResponse(call, Response.success(response.body()));
                        Log.i(TAG, "isSuccessful: " + response.body());
                    }
                } else {
                    callback.onFailure(call, new Throwable("Failed to fetch Cuisine"));
                    Log.i(TAG, "not: ");
                }
            }

            @Override
            public void onFailure(Call<CuisineResponse> call, Throwable t) {
                callback.onFailure(call, t);
                Log.i(TAG, "onFailure: ");
            }
        });
    }


    @Override
    public void getMealList(Callback<MealResponse> callback) {
        Call <MealResponse> call=apiService.getMeals();
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callback.onResponse(call, Response.success(response.body()));
                        Log.i(TAG, "isSuccessful: "+ response.body() );
                    }
                } else {
                    callback.onFailure(call, new Throwable("Failed to fetch meal"));
                    Log.i(TAG, "not: " );
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                callback.onFailure(call, t);
                Log.i(TAG, "onFailure: " );
            }
        });
    }

}
