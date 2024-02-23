package com.example.foodplanner.model.repositry.localrepo;

import android.annotation.SuppressLint;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.data.MealPlane;
import com.example.foodplanner.model.firebase.datasource.MealRemoteDatasourceImp;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class MealRepoImp implements MealRepo {
    private final MealLocalDatasource mealLocalDatasource;
    private static MealRepoImp mealRepoImp;
    private final MealRemoteDatasourceImp remoteDatasourceImp;
    public static MealRepoImp getInstance(MealLocalDatasource mealLocalDatasource)
    {
        if(mealRepoImp==null)
        {
            mealRepoImp=new MealRepoImp(mealLocalDatasource);
        }
        return mealRepoImp;
    }
    private MealRepoImp (MealLocalDatasource mealLocalDatasource)
    {
        this.mealLocalDatasource=mealLocalDatasource;
        this.remoteDatasourceImp = new MealRemoteDatasourceImp();
    }
    @Override
    public Completable insertProductToFavorite(Meal meal) {
        return mealLocalDatasource.insertProductToFavorite(meal)
                .doOnComplete(() -> insertProductToFavoriteRemote(meal));
    }

    @Override
    public void deleteFavoriteProduct(Meal meal) {
        mealLocalDatasource.deleteFavoriteProduct(meal);
        deleteMealInRemote(meal);
    }

    @Override
    public Flowable<List<Meal>> getFavProducts() {
        return mealLocalDatasource.getFavMeals();

    }

    @Override
    public Completable insertMealToPlane(MealPlane meal) {
        return mealLocalDatasource.insertMealToPlane(meal)
                .doOnComplete(() -> insertMealToPlaneRemote(meal));
    }

    @Override
    public void deleteMealPlane(MealPlane meal) {
          mealLocalDatasource.deleteMealPlane(meal);
          deletePlaneInRemote(meal);
    }

    @Override
    public Flowable<List<MealPlane>> getPlaneMeal() {
        return mealLocalDatasource.getPlaneMeals();
    }

    @SuppressLint("CheckResult")
    @Override
    public void insertProductToFavoriteRemote(Meal meal) {
        remoteDatasourceImp.insertProductToFavorite( meal)
                .subscribe(
                        () -> {
                        },
                        throwable -> {
                        }
                );
    }

    @SuppressLint("CheckResult")
    @Override
    public void insertMealToPlaneRemote(MealPlane mealPlane) {
        remoteDatasourceImp.insertMealToPlan(mealPlane)
                .subscribe(
                        () -> {
                        },
                        throwable -> {
                        }
                );
    }

    @Override
    public void deletePlaneInRemote(MealPlane mealPlane) {
        remoteDatasourceImp.deletePlane(mealPlane);
    }

    @Override
    public void deleteMealInRemote(Meal meal) {
        remoteDatasourceImp.deleteFav(meal);
    }

}
