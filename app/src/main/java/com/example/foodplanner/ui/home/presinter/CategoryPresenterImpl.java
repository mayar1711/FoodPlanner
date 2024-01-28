package com.example.foodplanner.ui.home.presinter;


import com.example.foodplanner.model.repositry.RepositoryInterface;
import com.example.foodplanner.model.response.CategoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenterImpl implements CategoryPresenter  {
    private final RepositoryInterface categoryRepository;
    private final CategoryView categoryView;


    public CategoryPresenterImpl(RepositoryInterface categoryRepository, CategoryView categoryView) {
        this.categoryRepository = categoryRepository;
        this.categoryView = categoryView;
    }

    @Override
    public void getCategories() {
        categoryRepository.getCategories(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        categoryView.showCategories(response.body().getCategories());
                    }
                } else {
                    categoryView.showError("Failed to fetch categories");
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                categoryView.showError(t.getMessage());
            }
        });
    }

}