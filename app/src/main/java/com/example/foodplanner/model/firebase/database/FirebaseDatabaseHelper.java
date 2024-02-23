package com.example.foodplanner.model.firebase.database;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.data.MealPlane;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDatabaseHelper {
    private DatabaseReference databaseReference;

    public FirebaseDatabaseHelper() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void addFavoriteMeal(String userEmail, Meal meal) {
        databaseReference.child("favorites").child(userEmail).child(meal.getIdMeal()).setValue(meal);
    }

    public void removeFavoriteMeal(String userEmail, Meal meal) {
        databaseReference.child("favorites").child(userEmail).child(meal.getIdMeal()).removeValue();
    }

    public void addMealPlan(String userEmail, MealPlane meal) {
        databaseReference.child("mealPlans").child(userEmail).child(meal.getIdMeal()).setValue(meal);
    }

    public void removeMealPlan(String userEmail, MealPlane meal) {
        databaseReference.child("mealPlans").child(userEmail).child(meal.getIdMeal()).removeValue();
    }
}
