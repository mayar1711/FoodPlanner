package com.example.foodplanner.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.foodplanner.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_favorite, R.id.navigation_search, R.id.planeFragment)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home); // Initialize the field here
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.searchByNameFragment ||
                    destination.getId() == R.id.mealDetailFragment ||
                    destination.getId() == R.id.categoryList ||
                    destination.getId() ==R.id.mealById||
                    destination.getId()==R.id.mealByIngradient||
                    destination.getId() == R.id.getMealByCuisineFragment) {
                toolbar.setVisibility(View.GONE);
                navView.setVisibility(View.GONE);
            } else {
                toolbar.setVisibility(View.GONE);
                navView.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}
