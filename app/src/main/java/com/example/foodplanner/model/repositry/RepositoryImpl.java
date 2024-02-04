package com.example.foodplanner.model.repositry;

import android.util.Log;
import com.example.foodplanner.model.network.ApiService;
import com.example.foodplanner.model.response.CategoryResponse;
import com.example.foodplanner.model.response.CuisineResponse;
import com.example.foodplanner.model.response.MealPreviewResponse;
import com.example.foodplanner.model.response.MealResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryImpl implements RepositoryInterface {
    private final ApiService apiService;
    private static final String TAG ="repo";
    public RepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

/*
    @Override
    public void getCategories(Callback<CategoryResponse> callback) {
    */
/*    Call<CategoryResponse> call = apiService.getCategories();
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
        });*//*

    }
*/

    @Override
    public Disposable getCategories(io.reactivex.rxjava3.core.SingleObserver<CategoryResponse> observer) {
        return apiService.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer::onSuccess, observer::onError);
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

    @Override
    public Single<MealPreviewResponse> getMealsByCategory(String category) {
        Log.d("RepositoryImpl", "Category for API call: " + category);
        return apiService.getMealsByCategory(category);
    }

}
