package com.example.foodplanner.ui.authentication.login.Presenter;

import com.example.foodplanner.model.firebase.AuthListener;
import com.example.foodplanner.model.firebase.AuthRepository;
import com.example.foodplanner.ui.authentication.login.view.LoginView;

public class LoginPresenterImp implements LoginPresenter , AuthListener {
     private final LoginView view;
     private final AuthRepository repository;

     public LoginPresenterImp(AuthRepository repository , LoginView view)
     {
         this.repository=repository;
         this.view=view;
     }

    @Override
    public void signInWithEmail(String email, String password) {
        view.showLoading();
        repository.signInWithEmail(email, password, this);
    }
    @Override
    public void onSuccess() {
        view.hideLoading();
        view.navigateToHome();
    }

    @Override
    public void onFailure(String error) {
        view.hideLoading();
        view.showError(error);
    }
}
