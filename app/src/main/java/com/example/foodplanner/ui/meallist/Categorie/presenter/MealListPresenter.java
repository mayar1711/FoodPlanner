package com.example.foodplanner.ui.meallist.Categorie.presenter;

import android.util.Log;
import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.repositry.remoterepo.RepositoryInterface;
import com.example.foodplanner.model.response.MealResponse;
import com.example.foodplanner.ui.meallist.Categorie.view.MealListView;
import java.util.ArrayList;
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
                .subscribe(new SingleObserver<MealResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onSuccess(MealResponse response) {
                        if (response != null) {
                            Log.d("MealListPresenter", "API Response: " + response.toString());
                            if (response.getMeals() != null) {
                                view.showMealList((ArrayList<Meal>) response.getMeals());
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
