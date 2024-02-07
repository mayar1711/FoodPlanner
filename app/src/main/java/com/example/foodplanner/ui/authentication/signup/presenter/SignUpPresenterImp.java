package com.example.foodplanner.ui.authentication.signup.presenter;

import com.example.foodplanner.model.firebase.AuthListener;
import com.example.foodplanner.model.firebase.AuthRepository;
import com.example.foodplanner.ui.authentication.signup.view.SignUp;

public class SignUpPresenterImp implements SignUpPresenter, AuthListener {
    private final AuthRepository repository;
    private final SignUp view;
    public SignUpPresenterImp(AuthRepository repository , SignUp view)
    {
        this.repository=repository;
        this.view=view;
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

    @Override
    public void signUpWithEmail(String email, String password) {
        view.showLoading();
        repository.signUpWithEmail(email, password, this);
    }
}
