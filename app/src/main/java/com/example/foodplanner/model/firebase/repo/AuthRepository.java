package com.example.foodplanner.model.firebase.repo;

import com.example.foodplanner.model.firebase.repo.AuthListener;

public interface AuthRepository {
    void signInWithEmail(String email, String password, AuthListener listener);
    void signUpWithEmail(String email, String password, AuthListener listener);
    void signUpWithGoogle(AuthListener listener);
    void signOut();

}
