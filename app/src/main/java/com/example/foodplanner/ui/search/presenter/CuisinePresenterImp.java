package com.example.foodplanner.ui.search.presenter;

import com.example.foodplanner.model.repositry.RepositoryInterface;
import com.example.foodplanner.model.response.CuisineResponse;

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
    public void getCuisines() {
        cuisineRepository.getCuisine(new Callback<CuisineResponse>() {
            @Override
            public void onResponse(Call<CuisineResponse> call, Response<CuisineResponse> response) {
                if (response.isSuccessful())
                {
                    if(response.body()!=null)
                    {
                        cuisineView.showCuisine(response.body().getCuisines());
                    }
                }
                else
                {
                    cuisineView.showError("Failed to fetch Cuisines ");
                }

            }

            @Override
            public void onFailure(Call<CuisineResponse> call, Throwable t) {
                cuisineView.showError(t.getMessage());

            }
        });
    }
}
