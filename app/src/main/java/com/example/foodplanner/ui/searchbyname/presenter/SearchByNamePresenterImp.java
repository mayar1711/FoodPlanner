package com.example.foodplanner.ui.searchbyname.presenter;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.repositry.remoterepo.RepositoryInterface;
import com.example.foodplanner.model.response.MealResponse;
import com.example.foodplanner.ui.searchbyname.view.SearchByNameView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchByNamePresenterImp implements SearchByNamePresenter{
    private final RepositoryInterface repository;
    private final SearchByNameView view;
    public SearchByNamePresenterImp(RepositoryInterface repository ,SearchByNameView view)
    {
        this.repository=repository;
        this.view=view;
    }
    @Override
    public void searchMealsByName(String name) {
        if (name.isEmpty()) {
            view.displaySearchResults(new ArrayList<>());
        } else {
            repository.getMealByName(name)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<MealResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }
                        @Override
                        public void onSuccess(MealResponse mealResponse) {
                            List<Meal> filteredMeals = filterMealsByName(mealResponse.getMeals(), name);
                            view.displaySearchResults(filteredMeals);
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.displayError("Error fetching meals: " + e.getMessage());
                        }
                    });
        }
    }

    private List<Meal> filterMealsByName(List<Meal> meals, String name) {
        List<Meal> filteredList = new ArrayList<>();
        if (meals != null) {
            for (Meal meal : meals) {
                if (meal.getStrMeal().toLowerCase().contains(name.toLowerCase())) {
                    filteredList.add(meal);
                }
            }
        }
        return filteredList;
    }

}