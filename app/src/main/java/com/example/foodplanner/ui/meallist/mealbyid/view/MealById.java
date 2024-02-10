package com.example.foodplanner.ui.meallist.mealbyid.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.model.data.GetArrayFromMeal;
import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.data.MealPlane;
import com.example.foodplanner.model.network.ApiClient;
import com.example.foodplanner.model.repositry.localrepo.MealRepoImp;
import com.example.foodplanner.model.repositry.remoterepo.RepositoryImpl;
import com.example.foodplanner.model.repositry.localrepo.MealLocalDatasourceImp;
import com.example.foodplanner.ui.authentication.MainActivity;
import com.example.foodplanner.ui.mealdetail.presinter.GetIdFromYoutubeUrl;
import com.example.foodplanner.ui.meallist.mealbyid.presenter.MealByIdPresenterImp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
    private ImageView plane;
    private Calendar selectedDateCalendar;
    String date;
    Meal meal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new MealByIdPresenterImp(new RepositoryImpl(ApiClient.getApiService()), this, MealRepoImp.getInstance(MealLocalDatasourceImp.getInstance(requireContext())));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_meal_by_id, container, false);
        ingredientsAdapter = new MealByIdIngredientsAdapter(new ArrayList<>());
        meal=(Meal) getArguments().getSerializable ("meal");
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("meal")) {
            Log.d("MealListFragment ById", "Category: " + meal);
            presenter.getMealById(meal.getIdMeal());
        }
        textViewMealName = view.findViewById(R.id.txtViewMealNameItemDetails);
        mealImage=view.findViewById(R.id.mealImage);
        categoryName=view.findViewById(R.id.tv_meal_category);
        plane = view.findViewById(R.id.imageViewAddToCalendarItemDetails);
        addToFav=view.findViewById(R.id.imageViewAddToFavITemDetails);
        placeholder=view.findViewById(R.id.textViewProcedures);
        area=view.findViewById(R.id.textViewMealCountryItemDetails);
        mealImage=view.findViewById(R.id.mealImage);
        recyclerView=view.findViewById(R.id.recyclerViewIngredientsItemDetails);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setAdapter(ingredientsAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        addToFav.setOnClickListener(v -> {
            if (currentUser!=null)
            addProductToFav(meal);
            else showAuthenticationAlert();
        });
        plane.setOnClickListener(v -> {
            if (currentUser!=null)
            showDatePickerDialog();
            else showAuthenticationAlert();
        });
        player=view.findViewById(R.id.ytPlayer);
        player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer player) {
                youTubePlayer = player;
            }
        });

        return view;
    }

    private void showDatePickerDialog() {
        final Calendar currentDateCalendar = Calendar.getInstance();
        int year = currentDateCalendar.get(Calendar.YEAR);
        int month = currentDateCalendar.get(Calendar.MONTH);
        int dayOfMonth = currentDateCalendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                (view, year1, monthOfYear, dayOfMonth1) -> {
                    selectedDateCalendar = Calendar.getInstance();
                    selectedDateCalendar.set(year1, monthOfYear, dayOfMonth1);
                    handleDateSelection(selectedDateCalendar.getTime());
                },
                year, month, dayOfMonth);
        datePickerDialog.getDatePicker().setMinDate(currentDateCalendar.getTimeInMillis());
        datePickerDialog.show();
    }
    private void handleDateSelection(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        this.date = sdf.format(date);
      //  Toast.makeText(requireActivity(), "date" +date, Toast.LENGTH_SHORT).show();
        MealPlane mealPlane = new MealPlane();
        mealPlane.setMealData(meal);
        addMealPlane(mealPlane);
    }

    @Override
    public void addMealPlane(MealPlane mealPlane) {
        mealPlane.setDate(date);
        presenter.addMealPlane(mealPlane);
        Log.i("TAG", "addMealPlane: "+mealPlane);
        Toast.makeText(requireActivity(), "meal added to plane", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMealById(Meal meal) {
        this.meal=meal;
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
    public void addProductToFav(Meal meal) {
        presenter.addProductToFav(meal);
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
    private void showAuthenticationAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Authentication Required")
                .setMessage("You need to log in to perform this action.")
                .setPositiveButton("Log In", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(requireActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()
                .show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.release();
    }


}