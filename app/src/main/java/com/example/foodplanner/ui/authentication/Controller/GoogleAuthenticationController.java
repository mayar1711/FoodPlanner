package com.example.foodplanner.ui.authentication.Controller;

import android.app.Activity;
import android.content.Intent;

import com.example.foodplanner.ui.authentication.model.GoogleAuthenticationModel;

public class GoogleAuthenticationController {

        private static GoogleAuthenticationController instance;
        private GoogleAuthenticationModel googleAuthenticationModel;

        private GoogleAuthenticationController() {
            googleAuthenticationModel = GoogleAuthenticationModel.getInstance();
        }

        public static GoogleAuthenticationController getInstance() {
            if (instance == null) {
                instance = new GoogleAuthenticationController();
            }
            return instance;
        }

        public void setGoogleAuthenticationListener(GoogleAuthenticationModel.AuthenticationListener listener) {
            googleAuthenticationModel.setAuthenticationListener(listener);
        }

        public void signInWithGoogle(Activity activity) {
            googleAuthenticationModel.signInWithGoogle(activity);
        }

        public void handleGoogleSignInResult(Intent data) {
            googleAuthenticationModel.handleGoogleSignInResult(data);
        }
    }

