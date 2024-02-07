package com.example.foodplanner.model.firebase;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.foodplanner.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthRepositoryImp implements AuthRepository {
    private FirebaseAuth firebaseAuth;
    private Context context;
    private GoogleSignInClient googleSignInClient;

    public AuthRepositoryImp(Context context) {
        firebaseAuth = FirebaseAuth.getInstance();
        this.context = context;
    }

    @Override
    public void signInWithEmail(String email, String password, AuthListener listener) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> listener.onSuccess())
                .addOnFailureListener(e -> listener.onFailure(e.getMessage()));
    }
    @Override
    public void signUpWithEmail(String email, String password, AuthListener listener) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> listener.onSuccess())
                .addOnFailureListener(e -> listener.onFailure(e.getMessage()));
    }

}
