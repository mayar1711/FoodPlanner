package com.example.foodplanner.ui.meallist.presenter;

import android.util.Log;

import com.example.foodplanner.model.repositry.RepositoryInterface;
import com.example.foodplanner.model.response.MealPreviewResponse;
import com.example.foodplanner.ui.meallist.view.MealListView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealListPresenter implements PresenterInterface {
    private final RepositoryInterface repository;
    private final MealListView view;

    public MealListPresenter(RepositoryInterface repository, MealListView view) {
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void getMealsByCategory(String category) {
        repository.getMealsByCategory(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MealPreviewResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Handle subscription
                    }
                    @Override
                    public void onSuccess(MealPreviewResponse response) {
                        if (response != null) {
                            Log.d("MealListPresenter", "API Response: " + response.toString());
                            if (response.mealPreviews != null) {
                                view.showMealList(response.mealPreviews);
                            } else {
                                view.showError("Response contains null data");
                            }
                        } else {
                            view.showError("Empty API response");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError("Error fetching meal list: " + e.getMessage());
                    }
                });
    }
}
