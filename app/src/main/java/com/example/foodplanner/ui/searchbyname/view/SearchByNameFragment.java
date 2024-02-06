package com.example.foodplanner.ui.searchbyname.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.network.ApiClient;
import com.example.foodplanner.model.repositry.RepositoryImpl;
import com.example.foodplanner.ui.meallist.Categorie.view.MealListAdapter;
import com.example.foodplanner.ui.searchbyname.presenter.SearchByNamePresenterImp;

import java.util.ArrayList;
import java.util.List;

public class SearchByNameFragment extends Fragment implements SearchByNameView {

    private SearchByNamePresenterImp presenter;
    private RecyclerView recyclerView;
    private SearchByNameAdapter adapter;
    private EditText search;
    public SearchByNameFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SearchByNamePresenterImp(new RepositoryImpl(ApiClient.getApiService()), this);
        adapter = new SearchByNameAdapter();

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_by_name, container, false);
        recyclerView = view.findViewById(R.id.searchByName_recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        search = view.findViewById(R.id.searchByName_editText);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchQuery = s.toString().trim();
                presenter.searchMealsByName(searchQuery);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return view;
    }
    @Override
    public void displaySearchResults(List<Meal> meals) {
        adapter.setMealPreviews((ArrayList<Meal>) meals);
        Log.i("TAG", "showMealList: "+meals.size());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void displayError(String msg) {

    }
}