package com.example.foodplanner.model.firebase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.foodplanner.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthRepositoryImp implements AuthRepository {
    private FirebaseAuth firebaseAuth;
    private Context context;
    private GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private AuthListener listener;
    private Activity activity;

    public AuthRepositoryImp(Context context, Activity activity, AuthListener listener) {
        firebaseAuth = FirebaseAuth.getInstance();
        this.context = context;
        this.activity = activity; // Initialize activity
        this.listener = listener; // Assign the listener
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(context, gso);
    }
    private FireBaseAuthWrapper fireBaseAuthWrapper;

    private AuthRepositoryImp()
     {
         this.fireBaseAuthWrapper=FireBaseAuthWrapper.getInstance();
     }
    private static AuthRepositoryImp instance;
    public static synchronized AuthRepositoryImp getInstance()
    {
        if(instance==null)
        {
            instance=new AuthRepositoryImp();
        }
        return instance;
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

    @Override
    public void signUpWithGoogle(AuthListener listener) {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        // Pass the listener to onActivityResult method
        activity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    // No need to override onActivityResult method here

    // Handle the result of Google sign-in here
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                listener.onFailure(e.getMessage());
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener(authResult -> listener.onSuccess())
                .addOnFailureListener(e -> listener.onFailure(e.getMessage()));
    }
}
