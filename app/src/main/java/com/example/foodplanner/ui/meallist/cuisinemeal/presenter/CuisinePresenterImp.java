package com.example.foodplanner.ui.meallist.cuisinemeal.presenter;

import android.util.Log;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.repositry.remoterepo.RepositoryInterface;
import com.example.foodplanner.model.response.MealResponse;
import com.example.foodplanner.ui.meallist.cuisinemeal.view.CuisineListView;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CuisinePresenterImp implements CuisinePresenter{
    private final RepositoryInterface repository;
    private final CuisineListView cuisineView;
    public CuisinePresenterImp(RepositoryInterface repository ,CuisineListView cuisineView)
    {
        this.repository=repository;
        this.cuisineView=cuisineView;
    }
    @Override
    public void getMealByCuisine(String cuisine) {
        repository.getMealsByCuisine(cuisine)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }
                    @Override
                    public void onSuccess(@NonNull MealResponse response) {
                        if (response != null) {
                            Log.d("MealListPresenter", "API Response: " + response.toString());
                            if (response.getMeals() != null) {
                                cuisineView.showResult((ArrayList<Meal>) response.getMeals());
                            } else {
                                cuisineView.showError("Response contains null data");
                            }
                        } else {
                            cuisineView.showError("Empty API response");
                        }
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        cuisineView.showError("Error fetching meal list: " + e.getMessage());
                    }
                }
        );
    }
}
