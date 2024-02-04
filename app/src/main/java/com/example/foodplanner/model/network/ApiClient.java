package com.example.foodplanner.model.network;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static ApiService apiService;

    private ApiClient() {

    }

    public static ApiService getApiService() {
        if (apiService == null) {
            apiService = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
                    .create(ApiService.class);
        }
        return apiService;
    }

}
