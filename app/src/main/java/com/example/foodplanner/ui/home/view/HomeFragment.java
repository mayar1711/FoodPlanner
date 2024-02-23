package com.example.foodplanner.ui.home.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.utils.widget.ImageFilterButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.model.data.Category;
import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.firebase.repo.AuthRepositoryImp;
import com.example.foodplanner.model.network.ApiClient;
import com.example.foodplanner.model.network.ApiService;
import com.example.foodplanner.model.repositry.remoterepo.RepositoryImpl;
import com.example.foodplanner.ui.authentication.MainActivity;
import com.example.foodplanner.ui.home.presinter.CategoryPresenter;
import com.example.foodplanner.ui.home.presinter.CategoryPresenterImpl;
import com.example.foodplanner.ui.home.presinter.MealPresenter;
import com.example.foodplanner.ui.home.presinter.MealPresenterImpl;

import java.io.Serializable;
import java.util.List;

public class HomeFragment extends Fragment implements OnCategoryClickListener,CategoryView, MealView {

    private CategoryAdapter categoryAdapter;
    private TextView meal;
    private ImageView imageView;
    private CardView mealRandom ;
    private ImageFilterButton logout;
    private ImageView noInternetImageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("category")) {
            Category category = (Category) bundle.getSerializable("category");
            if (category != null) {
                String categoryName = category.getStrCategory();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        meal=view.findViewById(R.id.tv_meal_day);
        imageView=view.findViewById(R.id.imageViewOfTheDay);
        mealRandom=view.findViewById(R.id.cardViewRandomMeal);
        noInternetImageView=view.findViewById(R.id.imageView2);
        checkInternetConnection();
        categoryAdapter = new CategoryAdapter();
        categoryAdapter.setClickListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(categoryAdapter);
        ApiService apiService = ApiClient.getApiService();
        CategoryPresenter categoryPresenter = new CategoryPresenterImpl(RepositoryImpl.getInstance(apiService), this);
        categoryPresenter.getCategories();
        MealPresenter presenter = new MealPresenterImpl(RepositoryImpl.getInstance(apiService), this);
        presenter.getMealList();
        logout=view.findViewById(R.id.logout);
        logout.setOnClickListener(v -> {
            AuthRepositoryImp.getInstance().signOut();
            Intent intent = new Intent(requireActivity(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }
    @Override
    public void onClickCategory(Category category) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("category", (Serializable) category);
        Navigation.findNavController(requireView()).navigate(R.id.action_navigation_home_to_categoryList, bundle);
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
                    mealRandom.setVisibility(View.GONE);
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
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void showCategories(List<Category> categories) {
        categoryAdapter.setCategories(categories);
        categoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void showError(String error) {
        Toast.makeText(requireActivity(), "Error: " + error, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void displayMeals(List<Meal> meals) {
        if (meals != null && !meals.isEmpty()) {
            Meal mealOfTheDay = meals.get(0);
            meal.setText(mealOfTheDay.getStrMeal());
            String imageUrl = mealOfTheDay.getStrMealThumb();
            Glide.with(requireContext())
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
            mealRandom.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("meal", meals.get(0));
                Navigation.findNavController(requireView()).navigate(R.id.action_navigation_home_to_mealDetailFragment,bundle);
            });
        } else {
            meal.setText("No meals available");
            Toast.makeText(requireActivity(), "error", Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public void displayError(String message) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();

    }
}
