package com.example.foodplanner.model.repositry.remoterepo;

import android.util.Log;

import com.example.foodplanner.model.data.Ingredient;
import com.example.foodplanner.model.network.ApiService;
import com.example.foodplanner.model.response.CategoryResponse;
import com.example.foodplanner.model.response.CuisineResponse;
import com.example.foodplanner.model.response.IngredientResponse;
import com.example.foodplanner.model.response.MealResponse;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RepositoryImpl implements RepositoryInterface {
    private final ApiService apiService;
    private static final String TAG ="repo";
    public RepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Disposable getCategories(io.reactivex.rxjava3.core.SingleObserver<CategoryResponse> observer) {
        return apiService.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer::onSuccess, observer::onError);
    }

    @Override
    public Disposable getCuisine(SingleObserver<CuisineResponse> observer) {
        return apiService.getCuisine()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer::onSuccess, observer::onError);
    }

    @Override
    public Disposable getMealList(SingleObserver<MealResponse> observer) {
        return apiService.getMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer::onSuccess, observer::onError);
    }

    @Override
    public Disposable getIngredient(SingleObserver<IngredientResponse> observer) {
        return apiService.getIngredients()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ingredientResponse -> {

                    List<Ingredient> ingredients = ingredientResponse.getIngredients().stream().limit(30).collect(Collectors.toList());
                    ingredientResponse.setIngredients(ingredients);
                    return ingredientResponse;
                })
                .subscribe(observer::onSuccess, observer::onError);
    }

    @Override
    public Single<MealResponse> getMealsByCategory(String category) {
        Log.d("RepositoryImpl", "Category for API call: " + category);
        return apiService.getMealsByCategory(category);
    }

    @Override
    public Single<MealResponse> getMealsByCuisine(String cuisine) {
        return apiService.getMealsByCuisine(cuisine);
    }

    @Override
    public Single<MealResponse> getMealByIngredient(String ingredient) {
        return apiService.getMealsByIngredient(ingredient);
    }

    @Override
    public Single<MealResponse> getMealById(String id) {
        return apiService.getMealById(id);
    }

    @Override
    public Single<MealResponse> getMealByName(String name) {
        return apiService.searchByName(name);
    }

}
