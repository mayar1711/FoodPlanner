package com.example.foodplanner.model.repositry;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.repositry.localrepo.MealLocalDatasource;
import com.example.foodplanner.model.response.MealResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class MealRepoImp implements MealRepo{
    private MealLocalDatasource mealLocalDatasource;
    private static MealRepoImp mealRepoImp;
    public static MealRepoImp getInstance(MealLocalDatasource mealLocalDatasource)
    {
        if(mealRepoImp==null)
        {
            mealRepoImp=new MealRepoImp(mealLocalDatasource);
        }
        return mealRepoImp;
    }
    public MealRepoImp (MealLocalDatasource mealLocalDatasource)
    {
        this.mealLocalDatasource=mealLocalDatasource;
    }
    @Override
    public Single<MealResponse> getAllProducts() {
        return null;
    }

    @Override
    public Completable insertProductToFavorite(Meal meal) {
        return mealLocalDatasource.insertProductToFavorite(meal);
    }

    @Override
    public void deleteFavoriteProduct(Meal meal) {
        mealLocalDatasource.deleteFavoriteProduct(meal);
    }

    @Override
    public Flowable<List<Meal>> getFavProducts() {
        return mealLocalDatasource.getFavMeals();

    }
}
