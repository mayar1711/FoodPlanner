package com.example.foodplanner.ui.meallist.Categorie.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.network.ApiClient;
import com.example.foodplanner.model.repositry.RepositoryImpl;
import com.example.foodplanner.ui.meallist.Categorie.presenter.MealListPresenter;

public class CategoryList extends Fragment  implements MealListView ,OnItemClickListener{
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
/*        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("meal")) {
            Meal meal = (Meal) bundle.getSerializable("meal");
            if (meal != null) {
                String mealName = meal.getStrMeal();
            }
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_meal_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        Category category=(Category)getArguments().getSerializable ("category");
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("category")) {
            Log.d("MealListFragment", "Category: " + category);
            presenter.getMealsByCategory(category.getStrCategory());
        }
        return view;
    }

    @Override
    public void showMealList(ArrayList<Meal> mealPreviews) {
        adapter.setMealPreviews(mealPreviews);
        Log.i("TAG", "showMealList: "+mealPreviews.size());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(requireContext(), "Error: " + error, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClickCategory(Meal meal) {
        Toast.makeText(requireActivity(), "Clicked category: " + meal.getStrMeal(), Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putSerializable("meal", (Serializable) meal);
        Navigation.findNavController(requireView()).navigate(R.id.action_categoryList_to_mealById,bundle);
    }
}