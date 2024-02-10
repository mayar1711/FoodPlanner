// FirebaseDatabaseHelper.java
package com.example.foodplanner.model.firebase;

import android.content.Context;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.data.MealPlane;
import com.example.foodplanner.model.local.MyDataBase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

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
    private String encodeEmailForFirebase(String email) {
        return email.replace(".", ",");
    }

    public void getAllFavorite( Context context) {
        String encodeEmail = encodeEmailForFirebase(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        databaseReference.child("favMeal")
                .child(encodeEmail)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Meal> favItemList = new ArrayList<>();

                        for(DataSnapshot data: snapshot.getChildren()){
                            favItemList.add(data.getValue(Meal.class));
                        }
                        new Thread(()->{  for (Meal item : favItemList){
                          MyDataBase.getInstance(context).getproductDao().insertProductToFavorite(item);
                        }}).start();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public void getAllFavoriteWeelPlan( Context context) {
        String encodeEmail = encodeEmailForFirebase(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        databaseReference.child("planMeal")
                .child(encodeEmail)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<MealPlane>weekPlanItemList = new ArrayList<>();

                        for(DataSnapshot data: snapshot.getChildren()){
                            weekPlanItemList.add(data.getValue(MealPlane.class));
                        }
                        new Thread(()->{  for (MealPlane weekPlan  : weekPlanItemList){
                            MyDataBase.getInstance(context).getproductDao().insertMealToPlane(weekPlan);
                        }}).start();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }


}
