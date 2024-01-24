package com.example.foodplanner.remote;

import com.example.foodplanner.model.repositories.mealsRepo.MealsRepositories;

public class APIServer implements RetrofitAPI {
    public static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static APIServer apiServer;
    public static synchronized APIServer getInstance() {
        if (apiServer == null) {
            apiServer = new APIServer();
        }
        return apiServer;
    }
}
