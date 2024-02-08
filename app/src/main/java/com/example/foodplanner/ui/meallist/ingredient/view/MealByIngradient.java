package com.example.foodplanner.ui.meallist.ingredient.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.data.Ingredient;
import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.network.ApiClient;
import com.example.foodplanner.model.repositry.remoterepo.RepositoryImpl;
import com.example.foodplanner.ui.meallist.ingredient.presenter.IngredientListPresenterImp;

import java.io.Serializable;
import java.util.ArrayList;

public class MealByIngradient extends Fragment implements IngredientListView , OnIngredientItemClick{

    private RecyclerView recyclerView;
    private IngredientAdapter ingredientAdapter;
    private IngredientListPresenterImp presenter;

    public MealByIngradient() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ingredientAdapter=new IngredientAdapter();
        presenter=new IngredientListPresenterImp(new RepositoryImpl(ApiClient.getApiService()), this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_meal_by_ingradient, container, false);
        recyclerView=view.findViewById(R.id.recycler_ingredient_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(ingredientAdapter);
        ingredientAdapter.setClicked(this);
        Ingredient ingredient=(Ingredient) getArguments().getSerializable ("ingredient");
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("ingredient")) {
            Log.d("MealListFragment", "ingredient: " + ingredient);
            presenter.getIngredientMealList(ingredient.getStrIngredient());
        }
        return view;
    }

    @Override
    public void showIngredientMealList(ArrayList<Meal> mealPreviews) {
         ingredientAdapter.setMealPreviews(mealPreviews);
         ingredientAdapter.notifyDataSetChanged();
    }

    @Override
    public void showIngredientError(String error) {
        Toast.makeText(requireContext(), "Error: " + error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClickIngredient(Meal meal) {
        Toast.makeText(requireActivity(), "Clicked Ingredient: " + meal.getStrMeal(), Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putSerializable("meal", (Serializable) meal);
        Navigation.findNavController(requireView()).navigate(R.id.action_mealByIngradient_to_mealById,bundle);

    }
}