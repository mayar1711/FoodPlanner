package com.example.foodplanner.model.firebase.repo;

public interface AuthListener {
    void onSuccess();
    void onFailure(String error);
}
