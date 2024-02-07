package com.example.foodplanner.model.firebase;

import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;

public interface AuthListener {
    void onSuccess();
    void onFailure(String error);

}
