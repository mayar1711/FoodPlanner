package com.example.foodplanner.ui.home.presinter;

import com.example.foodplanner.model.data.Meal;

import io.reactivex.rxjava3.disposables.Disposable;

public interface MealPresenter {
    Disposable getMealList();

}
