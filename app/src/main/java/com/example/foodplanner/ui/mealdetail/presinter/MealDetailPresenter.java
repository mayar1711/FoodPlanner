package com.example.foodplanner.ui.mealdetail.presinter;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.data.MealPlane;
import com.example.foodplanner.model.repositry.localrepo.MealRepo;
import com.example.foodplanner.ui.mealdetail.view.MealDetailContractView;

public class MealDetailPresenter  implements MealDetailContractPresenter {

    private MealRepo mealRepo;
    private MealDetailContractView view;
    private Meal meal;


    public MealDetailPresenter(MealDetailContractView view , MealRepo mealRepo) {
        this.view = view;
        this.mealRepo=mealRepo;
    }

    @Override
    public void setMealData(Meal meal) {
        this.meal = meal;
        view.displayMealDetails(meal);
    }

    @Override
    public void addProductToFavorite(Meal meal) {
        mealRepo.insertProductToFavorite(meal)
                .subscribe(
                        () -> view.onInsertSuccess(),
                        throwable -> view.onInsertError(throwable.getMessage())
                );
    }

    @Override
    public void addMealPlane(MealPlane mealPlane) {
        mealRepo.insertMealToPlane(mealPlane)
                .subscribe(
                        () -> view.onInsertSuccess(),
                        throwable -> view.onInsertError(throwable.getMessage())
                );
    }
}
