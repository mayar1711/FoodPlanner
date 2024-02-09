package com.example.foodplanner.model.firebase;
public class MealPlan {
    private String userEmail;
    private String mealId;
    private String date;

    public MealPlan() {

    }

    public MealPlan(String userEmail, String mealId, String date) {
        this.userEmail = userEmail;
        this.mealId = mealId;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}