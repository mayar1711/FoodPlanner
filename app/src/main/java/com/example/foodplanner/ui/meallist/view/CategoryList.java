package com.example.foodplanner.ui.meallist.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

import com.example.foodplanner.model.data.Category;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.data.MealPreview;
import com.example.foodplanner.model.network.ApiClient;
import com.example.foodplanner.model.repositry.RepositoryImpl;
import com.example.foodplanner.ui.meallist.presenter.MealListPresenter;
import com.example.foodplanner.ui.meallist.view.MealListView;

public class CategoryList extends Fragment  implements MealListView {
    private RecyclerView recyclerView;
    private MealListAdapter adapter;
    private MealListPresenter presenter;

    public CategoryList() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new MealListAdapter();
        presenter = new MealListPresenter(new RepositoryImpl(ApiClient.getApiService()), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_meal_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        Category category=(Category)getArguments().getSerializable ("category");
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("category")) {
            Log.d("MealListFragment", "Category: " + category);
            presenter.getMealsByCategory(category.getStrCategory());
        }


        return view;
    }

    @Override
    public void showMealList(ArrayList<MealPreview> mealPreviews) {
        adapter.setMealPreviews(mealPreviews);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(requireContext(), "Error: " + error, Toast.LENGTH_SHORT).show();
    }
}