package com.example.foodplanner.model.firebase;
public class MealFavorite {
    private String userEmail;
    private String mealId;

    public MealFavorite() {
    }

    public MealFavorite(String userEmail, String mealId) {
        this.userEmail = userEmail;
        this.mealId = mealId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }
}
