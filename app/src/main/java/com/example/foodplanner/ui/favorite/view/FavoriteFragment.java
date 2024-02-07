package com.example.foodplanner.ui.favorite.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.foodplanner.R;
import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.repositry.MealRepoImp;
import com.example.foodplanner.model.repositry.localrepo.MealLocalDatasourceImp;
import com.example.foodplanner.ui.favorite.presenter.FavMeal;
import com.example.foodplanner.ui.favorite.presenter.FavMealImp;
import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements FavMealView{
    private RecyclerView recyclerView;
    private FavMeal favMeal;
    private FavoriteMealAdapter adapter;
    public FavoriteFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favMeal= FavMealImp.getInstance(
                MealRepoImp.getInstance(
                        MealLocalDatasourceImp.getInstance(requireContext())
                ),
                this
        );
        adapter = new FavoriteMealAdapter(new ArrayList<>());
        recyclerView = view.findViewById(R.id.recycler1_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.onDeleteClickListener = this::deleteFavProduct;
        favMeal.getProducts();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_favorite, container, false);
        return view;
    }
    @Override
    public void deleteFavProduct(Meal meal) {
        favMeal.deleteFavoriteProduct(meal);
    }

    @Override
    public void onGetAllFavoriteProducts(List<Meal> favoriteMeal) {
        Log.i("TAG", "Received data: " + favoriteMeal.toString());
        adapter.changeData(favoriteMeal);
        adapter.notifyDataSetChanged();
        Log.i("TAG", "onGetAllFavoriteProducts: " + favoriteMeal.size());
    }

    @Override
    public void onGetAllFavoriteProductsError(String errorMessage) {
        Toast.makeText(requireActivity(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
    }
}