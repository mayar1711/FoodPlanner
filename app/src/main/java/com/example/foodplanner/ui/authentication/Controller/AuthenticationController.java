package com.example.foodplanner.ui.authentication.Controller;

import com.example.foodplanner.model.firebase.FirebaseAuthenticationModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationController {
    private static AuthenticationController instance;
    private final FirebaseAuthenticationModel authenticationModel;
    private AuthenticationController() {
        authenticationModel = new FirebaseAuthenticationModel();
    }
    public static AuthenticationController getInstance() {
        if (instance == null) {
            instance = new AuthenticationController();
        }
        return instance;
    }
    public void signIn(String email, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        authenticationModel.signIn(email, password, onCompleteListener);
    }
    public void signUp(String email, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        authenticationModel.signUp(email, password, onCompleteListener);
    }
    public void signOut() {
        authenticationModel.signOut();
    }
    public FirebaseUser getCurrentUser() {
        return authenticationModel.getCurrentUser();
    }
}
