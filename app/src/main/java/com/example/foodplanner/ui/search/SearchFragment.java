package com.example.foodplanner.ui.search;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.data.Cuisine;
import com.example.foodplanner.model.data.Ingredient;
import com.example.foodplanner.model.network.ApiClient;
import com.example.foodplanner.model.network.ApiService;
import com.example.foodplanner.model.repositry.remoterepo.RepositoryImpl;
import com.example.foodplanner.ui.search.cuisine.presenter.CuisinePresenter;
import com.example.foodplanner.ui.search.cuisine.presenter.CuisinePresenterImp;
import com.example.foodplanner.ui.search.cuisine.view.CuisineAdapter;
import com.example.foodplanner.ui.search.cuisine.view.CuisineView;
import com.example.foodplanner.ui.search.cuisine.view.OnCuisineClicked;
import com.example.foodplanner.ui.search.ingredient.presenter.IngredientPresenter;
import com.example.foodplanner.ui.search.ingredient.presenter.IngredientPresenterImp;
import com.example.foodplanner.ui.search.ingredient.view.IngredientAdapter;
import com.example.foodplanner.ui.search.ingredient.view.IngredientView;
import com.example.foodplanner.ui.search.ingredient.view.OnIngredientClick;

import java.io.Serializable;
import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
public class SearchFragment extends Fragment implements CuisineView, OnIngredientClick, IngredientView , OnCuisineClicked {
    private CuisineAdapter cuisineAdapter;
    private CuisinePresenter cuisinePresenter;
    private RecyclerView recyclerView;
    private IngredientAdapter ingredientAdapter;
    private IngredientPresenter ingredientPresenter;
    private RecyclerView ingrediantRecyclerView;
    private Disposable area;
    private Disposable ingredient;
    private ImageView noInternetImageView;
    private  TextView search;
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
        ApiService apiService= ApiClient.getApiService();
        ingrediantRecyclerView=view.findViewById(R.id.recyclerView);
        noInternetImageView=view.findViewById(R.id.imageView3);
        ingredientAdapter=new IngredientAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        ingrediantRecyclerView.setAdapter(ingredientAdapter);
        ingredientPresenter=new IngredientPresenterImp(RepositoryImpl.getInstance(apiService),this);
        ingredientPresenter.getIngredient();
        ingredientAdapter.setOnIngredientClick(this);
        recyclerView =view.findViewById(R.id.recycler_cuisine);
        cuisineAdapter=new CuisineAdapter();
        cuisineAdapter.setCuisineClicked(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(requireActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cuisineAdapter);
        cuisinePresenter=new CuisinePresenterImp(RepositoryImpl.getInstance(apiService),this);
        cuisinePresenter.getCuisines();
        search = view.findViewById(R.id.tv_search);
        search.setOnClickListener(v -> {
            Navigation.findNavController(requireView())
                    .navigate(R.id.action_navigation_search_to_searchByNameFragment);
        });
        checkInternetConnection();
    }


    @Override
    public void showCuisine(List<Cuisine> cuisines) {
        cuisineAdapter.setCuisineList(cuisines);
        cuisineAdapter.notifyDataSetChanged();
    }

    private void checkInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Network network = connectivityManager.getActiveNetwork();
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
                if (capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))) {
                    noInternetImageView.setVisibility(View.GONE);
                } else {
                    noInternetImageView.setVisibility(View.VISIBLE);
                    search.setVisibility(View.GONE);
                }
            } else {
                if (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected()) {
                    noInternetImageView.setVisibility(View.GONE);
                } else {
                    noInternetImageView.setVisibility(View.VISIBLE);
                }
            }
        } else {
            noInternetImageView.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void showIngrediant(List<Ingredient> ingredients) {
        ingredientAdapter.setIngredientList(ingredients);
        ingredientAdapter.notifyDataSetChanged();
    }

    @Override
    public void showIngredientError(String error) {
        Toast.makeText(requireActivity(), "Error: " + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(requireActivity(), "Error: " + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onIngredientClick(Ingredient ingredient) {
       // Toast.makeText(requireActivity(), "Clicked ingredient: " + ingredient.getStrIngredient(), Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ingredient", (Serializable) ingredient);
        Navigation.findNavController(requireView()).navigate(R.id.action_navigation_search_to_mealByIngradient, bundle);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(this.area!=null&&area.isDisposed())
        {
            area.dispose();
        }
        if (this.ingredient!=null&&ingredient.isDisposed())
        {
            ingredient.dispose();
        }
    }

    @Override
    public void onCuisineClicked(Cuisine cuisine) {
     //   Toast.makeText(requireActivity(), "Clicked cuisine: " + cuisine.getStrArea(), Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putSerializable("cuisine", (Serializable) cuisine);
        Navigation.findNavController(requireView()).navigate(R.id.action_navigation_search_to_getMealByCuisineFragment, bundle);

    }
}