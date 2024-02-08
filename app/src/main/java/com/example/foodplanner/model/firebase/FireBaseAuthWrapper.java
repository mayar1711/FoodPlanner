package com.example.foodplanner.model.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FireBaseAuthWrapper {
    private FirebaseAuth auth;
    private static FireBaseAuthWrapper fireBaseAuthWrapper;
    public static synchronized FireBaseAuthWrapper getInstance(){
        if(fireBaseAuthWrapper == null){
            fireBaseAuthWrapper = new FireBaseAuthWrapper();
        }
        return fireBaseAuthWrapper;
    }

    private FireBaseAuthWrapper() {
        this.auth = FirebaseAuth.getInstance();
    }

    public FirebaseAuth getAuth() {
        return auth;
    }
    public FirebaseUser getCurrentUser(){
        return auth.getCurrentUser();
    }

    public void logout(){
        auth.signOut();
    }


}
