package com.example.foodplanner.ui.meallist.mealbyid.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.model.data.Category;
import com.example.foodplanner.model.data.GetArrayFromMeal;
import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.network.ApiClient;
import com.example.foodplanner.model.repositry.RepositoryImpl;
import com.example.foodplanner.ui.mealdetail.presinter.GetIdFromYoutubeUrl;
import com.example.foodplanner.ui.mealdetail.view.MealIngredientsAdapter;
import com.example.foodplanner.ui.meallist.mealbyid.presenter.MealByIdPresenter;
import com.example.foodplanner.ui.meallist.mealbyid.presenter.MealByIdPresenterImp;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class MealById extends Fragment implements MealByIdView {
     private MealByIdPresenterImp presenter;
    private TextView textViewMealName;
    private ImageView mealImage;
    private TextView categoryName;
    private TextView placeholder;
    private TextView area;
    private ImageView addToFav;
    private YouTubePlayerView player;
    private YouTubePlayer youTubePlayer;
    private RecyclerView recyclerView;
    private MealByIdIngredientsAdapter ingredientsAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new MealByIdPresenterImp(new RepositoryImpl(ApiClient.getApiService()), this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_meal_by_id, container, false);
        ingredientsAdapter = new MealByIdIngredientsAdapter(new ArrayList<>());
        Meal meal=(Meal) getArguments().getSerializable ("meal");
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("meal")) {
            Log.d("MealListFragment ById", "Category: " + meal);
            presenter.getMealById(meal.getIdMeal());
        }
        textViewMealName = view.findViewById(R.id.txtViewMealNameItemDetails);
        mealImage=view.findViewById(R.id.mealImage);
        categoryName=view.findViewById(R.id.tv_meal_category);
        addToFav=view.findViewById(R.id.imageViewAddToFavITemDetails);
        placeholder=view.findViewById(R.id.textViewProcedures);
        area=view.findViewById(R.id.textViewMealCountryItemDetails);
        mealImage=view.findViewById(R.id.mealImage);
        recyclerView=view.findViewById(R.id.recyclerViewIngredientsItemDetails);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setAdapter(ingredientsAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        player=view.findViewById(R.id.ytPlayer);
        player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer player) {
                youTubePlayer = player;
            }
        });

        return view;
    }


    @Override
    public void showMealById(Meal meal) {
        getActivity().runOnUiThread(() -> {
            textViewMealName.setText(meal.getStrMeal());
            categoryName.setText(meal.getStrCategory());
            Log.i("TAG", "displayMealDetails: " + meal.getStrCategory());
            area.setText(meal.strArea);
            Log.i("TAG", "displayMealDetails: " + meal.getStrArea());

            placeholder.setText(meal.strInstructions);
            Log.i("TAG", "displayMealDetails: " + meal.strInstructions);
            ingredientsAdapter.setList(GetArrayFromMeal.getArrayList(meal));

            Glide.with(requireContext())
                    .load(meal.getStrMealThumb())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(mealImage);
            if (youTubePlayer != null) {
                String videoId = GetIdFromYoutubeUrl.getId(meal.strYoutube);
                youTubePlayer.cueVideo(videoId, 0);
            }
        });
    }

    @Override
    public void showListError(String error) {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        player.release();
    }


}