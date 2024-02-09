package com.example.foodplanner.ui.home.presinter;


import com.example.foodplanner.model.repositry.remoterepo.RepositoryInterface;
import com.example.foodplanner.model.response.CategoryResponse;
import com.example.foodplanner.ui.home.view.CategoryAdapter;

import io.reactivex.rxjava3.disposables.Disposable;

public class CategoryPresenterImpl implements CategoryPresenter {
    private final RepositoryInterface categoryRepository;
    private final CategoryAdapter.CategoryView categoryView;

    public CategoryPresenterImpl(RepositoryInterface categoryRepository, CategoryAdapter.CategoryView categoryView) {
        this.categoryRepository = categoryRepository;
        this.categoryView = categoryView;
    }

    @Override
    public Disposable getCategories() {
        return categoryRepository.getCategories(new io.reactivex.rxjava3.core.SingleObserver<CategoryResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(CategoryResponse categoryResponse) {
                if (categoryResponse != null) {
                    categoryView.showCategories(categoryResponse.getCategories());
                } else {
                    categoryView.showError("Failed to fetch categories");
                }
            }

            @Override
            public void onError(Throwable e) {
                categoryView.showError(e.getMessage());
            }
        });
    }
}
