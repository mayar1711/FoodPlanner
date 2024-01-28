package com.example.foodplanner.ui.home.presinter;

import android.util.Log;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.repositry.RepositoryImpl;
import com.example.foodplanner.model.repositry.RepositoryInterface;
import com.example.foodplanner.model.response.MealResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealPresenterImpl implements MealPresenter {
    private final RepositoryInterface repository;
    private final MealView view;
    private static final String TAG="MealP";

    public MealPresenterImpl(RepositoryInterface repository, MealView view) {
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void getMealList() {
        repository.getMealList(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        MealResponse mealResponse = response.body();
                        List<Meal> meals = mealResponse.getMeals();
                        if (meals != null) {
                            Log.i(TAG, "Number of meals received: " + meals.size());
                            view.displayMeals(meals);
                        } else {
                            Log.e(TAG, "Received null list of meals");
                            view.displayError("Failed to fetch meals");
                        }
                    } else {
                        Log.e(TAG, "MealResponse body is null");
                        view.displayError("Failed to fetch meals");
                    }
                } else {
                    Log.e(TAG, "Failed to fetch meal. Response code: " + response.code());
                    view.displayError("Failed to fetch meals");
                }
            }




            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                view.displayError(t.getMessage());

            }
        });
    }
}

