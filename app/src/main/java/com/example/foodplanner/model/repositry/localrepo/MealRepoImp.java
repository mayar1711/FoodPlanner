package com.example.foodplanner.model.repositry.localrepo;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.data.MealPlane;
import com.example.foodplanner.model.firebase.MealRemoteDatasourceImp;
import com.example.foodplanner.model.response.MealResponse;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealRepoImp implements MealRepo {
    private MealLocalDatasource mealLocalDatasource;
    private static MealRepoImp mealRepoImp;
    private MealRemoteDatasourceImp remoteDatasourceImp;

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
        this.remoteDatasourceImp = new MealRemoteDatasourceImp();
    }
    @Override
    public Single<MealResponse> getAllProducts() {
        return null;
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

    @Override
    public void insertMealToPlaneRemote(MealPlane mealPlane) {
        // Assuming you have access to the user's email address
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
