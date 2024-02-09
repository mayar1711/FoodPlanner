package com.example.foodplanner.model.firebase;

import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.data.MealPlane;
import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.rxjava3.core.Completable;

public class MealRemoteDatasourceImp implements MealRemoteDatasource {
    private FirebaseDatabaseHelper firebaseDatabaseHelper;
    private FirebaseAuth firebaseAuth;

    public MealRemoteDatasourceImp() {
        firebaseDatabaseHelper = new FirebaseDatabaseHelper();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public Completable insertProductToFavorite(Meal meal) {
        String userEmail = firebaseAuth.getCurrentUser().getEmail();
        String encodedEmail = encodeEmailForFirebase(userEmail);
        firebaseDatabaseHelper.addFavoriteMeal(encodedEmail, meal);
        return Completable.complete();
    }
    private String encodeEmailForFirebase(String email) {
        // Replace characters not allowed in Firebase Database paths
        return email.replace(".", ",");
    }
    @Override
    public Completable insertMealToPlan( MealPlane meal) {
        String userEmail = firebaseAuth.getCurrentUser().getEmail();
        String encodedEmail = encodeEmailForFirebase(userEmail);
        firebaseDatabaseHelper.addMealPlan(encodedEmail, meal);
        return Completable.complete();
    }

    @Override
    public void deletePlane(MealPlane mealPlane) {
        String userEmail = firebaseAuth.getCurrentUser().getEmail();
        String encodedEmail = encodeEmailForFirebase(userEmail);
        firebaseDatabaseHelper.removeMealPlan(encodedEmail,mealPlane);
    }

    @Override
    public void deleteFav(Meal meal) {
        String userEmail = firebaseAuth.getCurrentUser().getEmail();
        String encodedEmail = encodeEmailForFirebase(userEmail);
        firebaseDatabaseHelper.removeFavoriteMeal(encodedEmail,meal);
    }


}
