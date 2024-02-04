package com.example.foodplanner.ui.favorite.presenter;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.repositry.MealRepo;
import com.example.foodplanner.ui.favorite.view.FavMealView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavMealImp implements FavMeal{
   private static FavMealImp favMealImp;
   public MealRepo mealRepo;
   private FavMealView favMealView;
   public static synchronized FavMealImp getInstance(MealRepo mealRepo , FavMealView favMealView)
   {
       if(favMealImp==null)
       {
           favMealImp=new FavMealImp(mealRepo,favMealView);
       }
       return favMealImp;
   }
   public FavMealImp(MealRepo mealRepo , FavMealView favMealView){
       this.favMealView=favMealView;
       this.mealRepo=mealRepo;
   }
    @Override
    public void getProducts() {
        mealRepo.getFavProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        products -> favMealView.onGetAllFavoriteProducts(products),
                        throwable -> favMealView.onGetAllFavoriteProductsError(throwable.getMessage())
                );
    }

    @Override
    public void deleteFavoriteProduct(Meal meal) {
         mealRepo.deleteFavoriteProduct(meal);
    }
}
