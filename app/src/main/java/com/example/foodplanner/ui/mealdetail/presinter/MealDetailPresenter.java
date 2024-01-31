package com.example.foodplanner.ui.mealdetail.presinter;

import com.example.foodplanner.model.data.Meal;

public class MealDetailPresenter  implements MealDetailContractPresenter {

    private MealDetailContractView view;
    private Meal meal;


    public MealDetailPresenter(MealDetailContractView view) {
        this.view = view;
    }

    @Override
    public void setMealData(Meal meal) {
        this.meal = meal;
        view.displayMealDetails(meal);
    }
}
