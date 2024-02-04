package com.example.foodplanner.ui.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
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
import com.example.foodplanner.model.network.ApiClient;
import com.example.foodplanner.model.network.ApiService;
import com.example.foodplanner.model.repositry.RepositoryImpl;
import com.example.foodplanner.ui.home.presinter.CategoryPresenter;
import com.example.foodplanner.ui.home.presinter.CategoryPresenterImpl;
import com.example.foodplanner.ui.home.presinter.MealPresenter;
import com.example.foodplanner.ui.home.presinter.MealPresenterImpl;

import java.io.Serializable;
import java.util.List;

public class HomeFragment extends Fragment implements OnCategoryClickListener, CategoryAdapter.CategoryView, MealView {

    private RecyclerView recyclerView;

    private CategoryPresenter categoryPresenter;

    private CategoryAdapter categoryAdapter;
    private MealPresenter presenter;
    private TextView meal;
    private ImageView imageView;
    private List<Meal> meals;
    private CardView mealRandom ;


    private static final String TAG ="home ";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("category")) {
            Category category = (Category) bundle.getSerializable("category");

            // Now you can use the 'category' object as needed
            if (category != null) {
                String categoryName = category.getStrCategory();
                // Do something with the category name
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
        recyclerView =view.findViewById(R.id.recycler);
        meal=view.findViewById(R.id.tv_meal_day);
        imageView=view.findViewById(R.id.imageViewOfTheDay);
        mealRandom=view.findViewById(R.id.cardViewRandomMeal);
        categoryAdapter = new CategoryAdapter();
        categoryAdapter.setClickListener(this);
        LinearLayoutManager linearLayoutManager1 =new LinearLayoutManager(requireActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(categoryAdapter);
        ApiService apiService = ApiClient.getApiService();
        categoryPresenter = new CategoryPresenterImpl(new RepositoryImpl(apiService), this);
        categoryPresenter.getCategories();
        presenter = new MealPresenterImpl(new RepositoryImpl(apiService),this);
        presenter.getMealList();

    }
    @Override
    public void onClickCategory(Category category) {
        Toast.makeText(requireActivity(), "Clicked category: " + category.getStrCategory(), Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putSerializable("category", (Serializable) category);
        Navigation.findNavController(requireView()).navigate(R.id.action_navigation_home_to_categoryList, bundle);
    }


    @Override
    public void showCategories(List<Category> categories) {
        categoryAdapter.setCategories(categories);
        categoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void showError(String error) {
        Toast.makeText(requireActivity(), "Error: " + error, Toast.LENGTH_SHORT).show();
    }

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
            Toast.makeText(requireActivity(), mealOfTheDay.getStrMeal(), Toast.LENGTH_SHORT).show();
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
