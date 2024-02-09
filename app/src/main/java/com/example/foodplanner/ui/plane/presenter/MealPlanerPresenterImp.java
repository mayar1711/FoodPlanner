package com.example.foodplanner.ui.plane.presenter;

import android.util.Log;

import com.example.foodplanner.model.data.MealPlane;
import com.example.foodplanner.model.repositry.localrepo.MealRepo;
import com.example.foodplanner.ui.plane.view.PlaneView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealPlanerPresenterImp implements MealPlanerPresenter{
    public MealRepo mealRepo;
    private PlaneView view;
    private static MealPlanerPresenterImp planerPresenterImp;
    public static synchronized MealPlanerPresenterImp getInstance(MealRepo repo , PlaneView view)
    {
        if(planerPresenterImp==null)
        {
            planerPresenterImp=new MealPlanerPresenterImp(repo , view);
        }
        return planerPresenterImp;
    }
    public MealPlanerPresenterImp(MealRepo mealRepo ,PlaneView view)
    {
        this.mealRepo=mealRepo;
        this.view=view;
    }
    @Override
    public void getPlane(PlaneView planeView, String selectedDate) {
        mealRepo.getPlaneMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(meals -> filterMealsByDate(meals, selectedDate))
                .subscribe(
                        plane -> {
                            Log.i("MealPlanerPresenterImp", "Filtered meals: " + plane);
                            planeView.onGetAllPlaneMeal(plane);
                        },
                        throwable -> {
                            Log.e("MealPlanerPresenterImp", "Error fetching meals: " + throwable.getMessage());
                            planeView.onGetAllPlaneMealError(throwable.getMessage());
                        }
                );
    }

    private List<MealPlane> filterMealsByDate(List<MealPlane> meals, String selectedDate) {
        List<MealPlane> filteredMeals = new ArrayList<>();
        for (MealPlane meal : meals) {
            if (meal.getDate().equals(selectedDate)) {
                filteredMeals.add(meal);
            }
        }
        return filteredMeals;
    }
    @Override
    public void deletePlaneMeal(MealPlane meal) {
            mealRepo.deleteMealPlane(meal);
    }

}
