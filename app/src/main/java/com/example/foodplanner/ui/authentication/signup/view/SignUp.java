package com.example.foodplanner.ui.authentication.signup.view;

public interface SignUp {
    void showLoading();
    void hideLoading();
    void showError(String error);
    void navigateToHome();
}
