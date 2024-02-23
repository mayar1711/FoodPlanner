package com.example.foodplanner.model.firebase.repo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.foodplanner.R;
import com.example.foodplanner.model.repositry.localrepo.MealLocalDatasourceImp;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class AuthRepositoryImp implements AuthRepository {
    private FirebaseAuth firebaseAuth;
    private Context context;
    private GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private AuthListener listener;
    private Activity activity;
    private MealLocalDatasourceImp mealLocalDatasourceImp;
    public AuthRepositoryImp(Context context, Activity activity, AuthListener listener) {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.context = context;
        this.activity = activity;
        this.listener = listener;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(context, gso);
       this.mealLocalDatasourceImp = MealLocalDatasourceImp.getInstance(context);
    }
    private FireBaseAuthWrapper fireBaseAuthWrapper;

    private AuthRepositoryImp()
     {
         this.firebaseAuth = FirebaseAuth.getInstance();
         this.fireBaseAuthWrapper=FireBaseAuthWrapper.getInstance();
         this. mealLocalDatasourceImp = MealLocalDatasourceImp.getInstance(context);

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
    public void signUpWithGoogle(AuthListener listener) {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, RC_SIGN_IN);
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
    @SuppressLint("CheckResult")
    @Override
    public void signOut() {
        if (firebaseAuth != null) {
            firebaseAuth.signOut();
            mealLocalDatasourceImp.deleteAllMeals().subscribe(() -> {

            }, throwable -> {
            });
            mealLocalDatasourceImp.deleteAllPlanes().subscribe(() -> {

            }, throwable -> {

            });
            Log.i("TAG", "signOut:Signed out successfully ");
        } else {
            Log.i("TAG", "signOut: Failed to sign out. Firebase Auth is null.");

        }
    }
}
