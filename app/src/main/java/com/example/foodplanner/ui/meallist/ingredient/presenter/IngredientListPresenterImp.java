package com.example.foodplanner.ui.meallist.ingredient.presenter;

import android.util.Log;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.repositry.remoterepo.RepositoryInterface;
import com.example.foodplanner.model.response.MealResponse;
import com.example.foodplanner.ui.meallist.ingredient.view.IngredientListView;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class IngredientListPresenterImp implements IngredientListPresenter{
    private final IngredientListView ingredientListView;
    private final RepositoryInterface repository;
    public IngredientListPresenterImp(RepositoryInterface repository ,IngredientListView ingredientListPresenter)
    {
        this.repository=repository;
        this.ingredientListView=ingredientListPresenter;
    }
    @Override
    public void getIngredientMealList(String ingredient) {
        repository.getMealByIngredient(ingredient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MealResponse>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull MealResponse response) {
                            if (response!=null) {
                                Log.d("MealListPresenter", "API Response: " + response.toString());
                                if (response.getMeals() != null) {
                                    ingredientListView.showIngredientMealList((ArrayList<Meal>) response.getMeals());
                                } else
                                    ingredientListView.showIngredientError("Response contains null data");
                            }
                            else {
                                ingredientListView.showIngredientError("Empty API response");
                            }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                       ingredientListView.showIngredientError("Error fetching meal list: " + e.getMessage());
                    }

                }
        );
    }
}