package com.example.foodplanner.model.firebase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.repositry.localrepo.MealLocalDatasourceImp;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        this.activity = activity; // Initialize activity
        this.listener = listener; // Assign the listener
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
    public void handleGoogleSignInResult(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            if (account != null) {
                firebaseAuthWithGoogle(account);
            }
        } catch (ApiException e) {
            Log.w("TAG", "Google sign in failed", e);
            listener.onFailure("Google sign in failed: " + e.getMessage());
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithCredential:success");
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        listener.onSuccess();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "signInWithCredential:failure", task.getException());
                        listener.onFailure("Authentication failed: " + task.getException().getMessage());
                    }
                });
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


    private String encodeEmailForFirebase(String email) {
        return email.replace(".", ",");
    }

    public void getUserEmail(String email, EmailListener listener) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        String encodedEmail = encodeEmailForFirebase(email); // Encode the email
        databaseReference.child(encodedEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String email = dataSnapshot.child("email").getValue(String.class);
                listener.onEmailReceived(email);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
