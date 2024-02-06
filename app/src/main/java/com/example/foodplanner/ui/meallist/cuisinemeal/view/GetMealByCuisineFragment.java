package com.example.foodplanner.ui.meallist.cuisinemeal.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.data.Category;
import com.example.foodplanner.model.data.Cuisine;
import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.network.ApiClient;
import com.example.foodplanner.model.repositry.RepositoryImpl;
import com.example.foodplanner.ui.meallist.cuisinemeal.presenter.CuisinePresenter;
import com.example.foodplanner.ui.meallist.cuisinemeal.presenter.CuisinePresenterImp;

import java.util.ArrayList;

public class GetMealByCuisineFragment extends Fragment implements CuisineListView {
    private RecyclerView recyclerView;
    private CuisineAdapter cuisineAdapter;
    private CuisinePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cuisineAdapter=new CuisineAdapter();
        presenter=new CuisinePresenterImp(new RepositoryImpl(ApiClient.getApiService()), this);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("cuisine")) {
            Meal meal = (Meal) bundle.getSerializable("cuisine");
            if (meal != null) {
                String mealName = meal.getIdMeal();
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_get_meal_by_cuisine, container, false);
        recyclerView=view.findViewById(R.id.recycler_cuisine_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(cuisineAdapter);
        Cuisine cuisine=(Cuisine) getArguments().getSerializable ("cuisine");
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("cuisine")) {
            Log.d("MealListFragment", "Category: " + cuisine);
            presenter.getMealByCuisine(cuisine.getStrArea());
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showResult(ArrayList<Meal> meals) {
        cuisineAdapter.setMealPreviews(meals);
        cuisineAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(requireContext(), "Error: " + error, Toast.LENGTH_SHORT).show();
    }

}