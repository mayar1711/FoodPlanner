package com.example.foodplanner.ui.search.ingredient.presenter;

import com.example.foodplanner.model.repositry.remoterepo.RepositoryInterface;
import com.example.foodplanner.model.response.IngredientResponse;
import com.example.foodplanner.ui.search.ingredient.view.IngredientView;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;

public class IngredientPresenterImp implements IngredientPresenter{
    private final RepositoryInterface repositoryInterface;
    private final IngredientView ingredientView;
    public IngredientPresenterImp(RepositoryInterface repositoryInterface,IngredientView ingredientView )
    {
        this.ingredientView=ingredientView;
        this.repositoryInterface=repositoryInterface;
    }
    @Override
    public Disposable getIngredient() {
        return repositoryInterface.getIngredient(new io.reactivex.rxjava3.core.SingleObserver<IngredientResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull IngredientResponse ingredientResponse) {
                      if (ingredientResponse!=null)
                      {
                          ingredientView.showIngrediant(ingredientResponse.getIngredients());
                      }
                      else
                          ingredientView.showIngredientError("Failed to fetch ingredient ");
            }
            @Override
            public void onError(@NonNull Throwable e) {
              ingredientView.showIngredientError(e.getMessage());
            }
        });
    }
}
