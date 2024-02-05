package com.example.foodplanner.ui.search.presenter;

import com.example.foodplanner.model.repositry.RepositoryInterface;
import com.example.foodplanner.model.response.CategoryResponse;
import com.example.foodplanner.model.response.CuisineResponse;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CuisinePresenterImp implements CuisinePresenter{
    private final RepositoryInterface cuisineRepository;
    private final CuisineView cuisineView;
    public CuisinePresenterImp(RepositoryInterface cuisineRepository, CuisineView cuisineView) {
        this.cuisineRepository = cuisineRepository;
        this.cuisineView = cuisineView;
    }


    @Override
    public Disposable getCuisines() {
        return cuisineRepository.getCuisine(new io.reactivex.rxjava3.core.SingleObserver<CuisineResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull CuisineResponse cuisineResponse) {
                if (cuisineResponse != null) {
                    cuisineView.showCuisine(cuisineResponse.getMeals());
                } else {
                    cuisineView.showError("Failed to fetch categories");
                }
            }
            @Override
            public void onError(@NonNull Throwable e) {
                cuisineView.showError(e.getMessage());
            }
        });
    }
}
