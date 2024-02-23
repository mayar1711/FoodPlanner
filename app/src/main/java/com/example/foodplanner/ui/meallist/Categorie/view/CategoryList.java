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
import com.example.foodplanner.model.repositry.remoterepo.RepositoryImpl;
import com.example.foodplanner.ui.meallist.Categorie.presenter.MealListPresenter;
import com.example.foodplanner.ui.meallist.Categorie.presenter.PresenterInterface;

public class CategoryList extends Fragment  implements MealListView ,OnItemClickListener{
    private MealListAdapter adapter;
    private PresenterInterface presenter;

    public CategoryList() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new MealListAdapter();
        presenter = new MealListPresenter(RepositoryImpl.getInstance(ApiClient.getApiService()), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_meal_list);
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
        Bundle bundle = new Bundle();
        bundle.putSerializable("meal", (Serializable) meal);
        Navigation.findNavController(requireView()).navigate(R.id.action_categoryList_to_mealById,bundle);
    }
}