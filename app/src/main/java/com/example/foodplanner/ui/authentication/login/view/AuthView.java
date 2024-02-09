package com.example.foodplanner.ui.authentication.login.view;

import com.google.firebase.auth.FirebaseUser;

public interface AuthView {
    void showLoading();
    void hideLoading();
    void showError(String error);
    void navigateToHome();
}
