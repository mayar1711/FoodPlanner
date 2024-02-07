package com.example.foodplanner.ui.mealdetail.view;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.model.data.GetArrayFromMeal;
import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.repositry.MealRepoImp;
import com.example.foodplanner.model.repositry.localrepo.MealLocalDatasourceImp;
import com.example.foodplanner.ui.mealdetail.presinter.GetIdFromYoutubeUrl;
import com.example.foodplanner.ui.mealdetail.presinter.MealDetailContractPresenter;
import com.example.foodplanner.ui.mealdetail.presinter.MealDetailPresenter;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class MealDetailFragment extends Fragment implements MealDetailContractView {
    private MealDetailContractPresenter presenter;
    private  TextView textViewMealName;
    private ImageView mealImage;
    private TextView categoryName;
    private TextView placeholder;
    private TextView area;
    private ImageView addToFav;
    private RecyclerView recyclerView;
    private YouTubePlayerView player;
    private MealIngredientsAdapter ingredientsAdapter;
    public MealDetailFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MealDetailPresenter(this, MealRepoImp.getInstance(MealLocalDatasourceImp.getInstance(requireContext())));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_meal_detail, container, false);
         textViewMealName = view.findViewById(R.id.txtViewMealNameItemDetails);
        ingredientsAdapter = new MealIngredientsAdapter(new ArrayList<>());
         mealImage=view.findViewById(R.id.mealImage);
         categoryName=view.findViewById(R.id.tv_meal_category);
         addToFav=view.findViewById(R.id.imageViewAddToFavITemDetails);
         placeholder=view.findViewById(R.id.textViewProcedures);
         area=view.findViewById(R.id.textViewMealCountryItemDetails);
         mealImage=view.findViewById(R.id.mealImage);
         recyclerView=view.findViewById(R.id.rvIngredientsItemDetails);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setAdapter(ingredientsAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
         player=view.findViewById(R.id.ytPlayer);
        Meal meal = (Meal) getArguments().getSerializable("meal");
        presenter.setMealData(meal);
        addToFav.setOnClickListener(v -> {
            addProductToFav(meal);
        });
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void displayMealDetails(Meal meal) {
        textViewMealName.setText(meal.getStrMeal());
        categoryName.setText(meal.getStrCategory());
        Log.i("TAG", "displayMealDetails: "+meal.getStrCategory());
        area.setText(meal.strArea);
        Log.i("TAG", "displayMealDetails: "+meal.getStrArea());

        placeholder.setText(meal.strInstructions);
        Log.i("TAG", "displayMealDetails: "+meal.strInstructions);
        ingredientsAdapter.setList(GetArrayFromMeal.getArrayList(meal));
        Log.i("TAG", "displayMealDetails: "+ GetArrayFromMeal.getArrayList(meal).size());
        Glide.with(requireContext())
                .load(meal.getStrMealThumb())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(mealImage);
        player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = GetIdFromYoutubeUrl.getId(meal.strYoutube);
                youTubePlayer.cueVideo(videoId, 0);
            }
        });
    }
    @Override
    public void displayError(String message) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
        Log.i("TAG", "displayError: "+message);
    }

    @Override
    public void addProductToFav(Meal meal) {
            presenter.addProductToFavorite(meal);
    }

    @Override
    public void onInsertSuccess() {
        Toast.makeText(requireActivity(), "meal added to favorites", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInsertError(String errorMessage) {
        Toast.makeText(requireActivity(), errorMessage, Toast.LENGTH_SHORT).show();
        Log.i("TAG", "onInsertError: "+errorMessage);

    }
}