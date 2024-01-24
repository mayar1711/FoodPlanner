package com.example.foodplanner.ui.authentication.model;

import android.app.Activity;
import android.content.Intent;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

public class GoogleAuthenticationModel {

    private static final int RC_SIGN_IN = 9001;

    private static GoogleAuthenticationModel instance;
    private AuthenticationListener authenticationListener;

    private GoogleAuthenticationModel() {
        // Private constructor to prevent instantiation outside the class
    }

    public static GoogleAuthenticationModel getInstance() {
        if (instance == null) {
            instance = new GoogleAuthenticationModel();
        }
        return instance;
    }

    public void setAuthenticationListener(AuthenticationListener listener) {
        this.authenticationListener = listener;
    }

    public void signInWithGoogle(Activity activity) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(activity, gso);

        Intent signInIntent = googleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void handleGoogleSignInResult(Intent data) {
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            // You can now use account.getIdToken() or account.getId() for authentication
            authenticationListener.onAuthenticationSuccess(account);
        } else {
            authenticationListener.onAuthenticationFailure(result.getStatus().getStatusCode());
        }
    }

    public interface AuthenticationListener {
        void onAuthenticationSuccess(GoogleSignInAccount account);

        void onAuthenticationFailure(int errorCode);
    }
}

