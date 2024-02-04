package com.example.foodplanner.ui.home.presinter;

import com.example.foodplanner.model.repositry.RepositoryInterface;
import com.example.foodplanner.model.response.MealResponse;
import com.example.foodplanner.ui.home.view.MealView;

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
                          view.displayMeals(response.body().getMeals());
                    } else {
                        view.displayError("Failed to fetch meals");
                    }
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                view.displayError(t.getMessage());

            }
        });
    }
}

