package com.example.foodplanner.ui.meallist.mealbyid.presenter;

import android.util.Log;
import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.repositry.RepositoryInterface;
import com.example.foodplanner.model.response.MealResponse;
import com.example.foodplanner.ui.meallist.mealbyid.view.MealByIdView;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class MealByIdPresenterImp implements MealByIdPresenter {
    private final RepositoryInterface repository;
    private final MealByIdView view;

    public MealByIdPresenterImp(RepositoryInterface repository, MealByIdView view) {
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void getMealById(String id) {
        repository.getMealById(id)
                .subscribe(new SingleObserver<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onSuccess(@NonNull MealResponse response) {
                        if (response != null) {
                            Log.d("MealListPresenter", "API Response: " + response.toString());
                            if (response.getMeals() != null && !response.getMeals().isEmpty()) {
                                // Call the updated method with the first meal from the list
                                view.showMealById(response.getMeals().get(0));
                            } else {
                                view.showListError("Response contains null or empty data");
                            }
                        } else {
                            view.showListError("Empty API response");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showListError("Error fetching meal: " + e.getMessage());
                    }
                });
    }
}
