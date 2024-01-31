package com.example.foodplanner.ui.mealdetail;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.ui.mealdetail.presinter.MealDetailContractPresenter;
import com.example.foodplanner.ui.mealdetail.presinter.MealDetailContractView;
import com.example.foodplanner.ui.mealdetail.presinter.MealDetailPresenter;

public class MealDetailFragment extends Fragment implements MealDetailContractView {
    private MealDetailContractPresenter presenter;
    private  TextView textViewMealName;
    private ImageView mealImage;
    private TextView categoryName;

    public MealDetailFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MealDetailPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_meal_detail, container, false);
         textViewMealName = view.findViewById(R.id.textViewMealCountryItemDetails);
         mealImage=view.findViewById(R.id.mealImage);
         categoryName=view.findViewById(R.id.tv_meal_category);
         mealImage=view.findViewById(R.id.mealImage);
        Meal meal = (Meal) getArguments().getSerializable("meal");
        presenter.setMealData(meal);
        return  view;
    }

    @Override
    public void displayMealDetails(Meal meal) {
        textViewMealName.setText(meal.getStrMeal());
        categoryName.setText(meal.getStrCategory());
        Glide.with(requireContext())
                .load(meal.getStrMealThumb())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(mealImage);
    }
    @Override
    public void displayError(String message) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();

    }
}