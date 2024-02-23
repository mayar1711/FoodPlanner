package com.example.foodplanner.ui.mealdetail.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.foodplanner.model.data.ArrayFromMeal;
import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.data.MealPlane;
import com.example.foodplanner.model.repositry.localrepo.MealRepoImp;
import com.example.foodplanner.model.repositry.localrepo.MealLocalDatasourceImp;
import com.example.foodplanner.ui.authentication.MainActivity;
import com.example.foodplanner.ui.mealdetail.presinter.GetIdFromYoutubeUrl;
import com.example.foodplanner.ui.mealdetail.presinter.MealDetailContractPresenter;
import com.example.foodplanner.ui.mealdetail.presinter.MealDetailPresenter;
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
    private ImageView plane;
    private Calendar selectedDateCalendar;
    String date;

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
        View view = inflater.inflate(R.layout.fragment_meal_detail, container, false);
        textViewMealName = view.findViewById(R.id.txtViewMealNameItemDetails);
        ingredientsAdapter = new MealIngredientsAdapter(new ArrayList<>());
        mealImage = view.findViewById(R.id.mealImage);
        categoryName = view.findViewById(R.id.tv_meal_category);
        addToFav = view.findViewById(R.id.imageViewAddToFavITemDetails);
        placeholder = view.findViewById(R.id.textViewProcedures);
        area = view.findViewById(R.id.textViewMealCountryItemDetails);
        mealImage = view.findViewById(R.id.mealImage);
        plane = view.findViewById(R.id.imageViewAddToCalendarItemDetails);
        recyclerView = view.findViewById(R.id.rvIngredientsItemDetails);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setAdapter(ingredientsAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        player = view.findViewById(R.id.ytPlayer);

        Meal meal = (Meal) getArguments().getSerializable("meal");
        MealPlane mealPlane = new MealPlane();
        mealPlane.setMealData(meal);
        presenter.setMealData(meal);
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

        return view;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
     //   Toast.makeText(requireActivity(), "date" +date, Toast.LENGTH_SHORT).show();
        Meal meal = (Meal) getArguments().getSerializable("meal");
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
    public void displayMealDetails(Meal meal) {
        textViewMealName.setText(meal.getStrMeal());
        categoryName.setText(meal.getStrCategory());
        Log.i("TAG", "displayMealDetails: "+meal.getStrCategory());
        area.setText(meal.strArea);
        Log.i("TAG", "displayMealDetails: "+meal.getStrArea());
        placeholder.setText(meal.strInstructions);
        Log.i("TAG", "displayMealDetails: "+meal.strInstructions);
        ingredientsAdapter.setList(ArrayFromMeal.getArrayList(meal));
        Log.i("TAG", "displayMealDetails: "+ ArrayFromMeal.getArrayList(meal).size());
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
     //   Toast.makeText(requireActivity(), errorMessage, Toast.LENGTH_SHORT).show();
        Log.i("TAG", "onInsertError: "+errorMessage);

    }

}