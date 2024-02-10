package com.example.foodplanner.model.firebase;

import android.content.Intent;

public interface AuthRepository {
    void signInWithEmail(String email, String password, AuthListener listener);
    void signUpWithEmail(String email, String password, AuthListener listener);
    void signUpWithGoogle(AuthListener listener);
    void getUserEmail(String userId, EmailListener listener);
    void signOut();
    void handleGoogleSignInResult(Intent data);
}
