package com.example.foodplanner.ui.authentication.login.view;

public interface LoginView {
    void showLoading();
    void hideLoading();
    void showError(String error);
    void navigateToHome();
}
