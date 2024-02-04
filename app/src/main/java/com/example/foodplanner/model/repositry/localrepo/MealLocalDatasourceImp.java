package com.example.foodplanner.model.repositry.localrepo;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.local.MealDao;
import com.example.foodplanner.model.local.MyDataBase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealLocalDatasourceImp implements MealLocalDatasource{
    private MealDao mealDao;
    private LiveData<List<Meal>> meals;
    private Flowable<List<Meal>> favMeals;
    private static MealLocalDatasourceImp localDatasourceImp;
    private MyDataBase dataBase;
    public MealLocalDatasourceImp(Context context)
    {
        dataBase=MyDataBase.getInstance(context);
        mealDao=dataBase.getproductDao();
        favMeals=mealDao.getAllProducts();
    }
    public static synchronized MealLocalDatasourceImp getInstance(Context context){
        if(localDatasourceImp==null)
        {
            localDatasourceImp=new MealLocalDatasourceImp(context);
        }
        return localDatasourceImp;
    }
    @Override
    public Completable insertProductToFavorite(Meal meal) {
        return mealDao.insertProductToFavorite(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    @Override
    public void deleteFavoriteProduct(Meal meal) {
        new Thread(() -> mealDao.deleteProductFromFavorite(meal)){
        }.start();
    }

    @Override
    public Flowable<List<Meal>> getFavMeals() {
        if (favMeals == null) {
            favMeals = mealDao.getAllProducts();
        }
        return favMeals;
    }
    public LiveData<List<Meal>> getProducts() {
        return meals;
    }

}
