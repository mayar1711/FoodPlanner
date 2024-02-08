package com.example.foodplanner.ui.plane.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;
import com.example.foodplanner.R;
import com.example.foodplanner.model.data.MealPlane;
import com.example.foodplanner.model.repositry.MealRepoImp;
import com.example.foodplanner.model.repositry.localrepo.MealLocalDatasourceImp;
import com.example.foodplanner.ui.plane.presenter.MealPlanerPresenter;
import com.example.foodplanner.ui.plane.presenter.MealPlanerPresenterImp;
import java.util.ArrayList;
import java.util.List;

public class PlaneFragment extends Fragment implements PlaneView {
    private MealPlanerPresenter presenter;
    private RecyclerView recyclerView;
    private PLaneAdapter adapter;
    private CalendarView calendarView;
    private Button navigateToSearch ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter=new PLaneAdapter(new ArrayList<>());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_plane, container, false);
        presenter= MealPlanerPresenterImp.getInstance(
                MealRepoImp.getInstance(
                        MealLocalDatasourceImp.getInstance(requireContext())
                ),
                this
        );
        recyclerView = view.findViewById(R.id.mealWeekPlan_recyclerView_weekplan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.onDeleteClickListener = this::deletePlaneMeal;
        presenter.getPlane(this);
        navigateToSearch=view.findViewById(R.id.addMeal_button_weekplan);
        navigateToSearch.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_planeFragment_to_navigation_search
            );
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void deletePlaneMeal(MealPlane meal) {
        presenter.deletePlaneMeal(meal);
    }

    @Override
    public void onGetAllPlaneMeal(List<MealPlane> favoriteMeal) {
        Log.i("TAG", "Received data: " + favoriteMeal.toString());
        adapter.changeData(favoriteMeal);
        adapter.notifyDataSetChanged();
        Log.i("TAG", "onGetAllFavoriteProducts: " + favoriteMeal.size());
    }

    @Override
    public void onGetAllPlaneMealError(String errorMessage) {
        Toast.makeText(requireActivity(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();

    }
}