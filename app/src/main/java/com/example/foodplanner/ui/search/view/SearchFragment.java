package com.example.foodplanner.ui.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.data.Cuisine;
import com.example.foodplanner.model.network.ApiClient;
import com.example.foodplanner.model.network.ApiService;
import com.example.foodplanner.model.repositry.RepositoryImpl;
import com.example.foodplanner.ui.search.presenter.CuisinePresenter;
import com.example.foodplanner.ui.search.presenter.CuisinePresenterImp;
import com.example.foodplanner.ui.search.presenter.CuisineView;

import java.util.List;

public class SearchFragment extends Fragment implements CuisineView {
    private CuisineAdapter cuisineAdapter;
    private CuisinePresenter cuisinePresenter;
    private RecyclerView recyclerView;
    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_search, container, false);

        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView =view.findViewById(R.id.recycler_cuisine);
        cuisineAdapter=new CuisineAdapter();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(requireActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cuisineAdapter);
        ApiService apiService= ApiClient.getApiService();
        cuisinePresenter=new CuisinePresenterImp(new RepositoryImpl(apiService),this);
        cuisinePresenter.getCuisines();
    }


    @Override
    public void showCuisine(List<Cuisine> cuisines) {
        cuisineAdapter.setCuisineList(cuisines);
        cuisineAdapter.notifyDataSetChanged();
        Toast.makeText(requireActivity(), "successes : " + cuisines, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showError(String error) {
        Toast.makeText(requireActivity(), "Error: " + error, Toast.LENGTH_SHORT).show();
    }
}