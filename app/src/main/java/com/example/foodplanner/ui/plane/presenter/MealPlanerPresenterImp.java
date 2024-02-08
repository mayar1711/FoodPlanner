package com.example.foodplanner.ui.plane.presenter;

import com.example.foodplanner.model.data.MealPlane;
import com.example.foodplanner.model.repositry.MealRepo;
import com.example.foodplanner.ui.plane.view.PlaneView;
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
    public void getPlane(PlaneView planeView) {
        mealRepo.getPlaneMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        plane->planeView.onGetAllPlaneMeal(plane),
                        throwable->planeView.onGetAllPlaneMealError(throwable.getMessage())
                );

    }
    @Override
    public void deletePlaneMeal(MealPlane meal) {
            mealRepo.deleteMealPlane(meal);
    }

}
