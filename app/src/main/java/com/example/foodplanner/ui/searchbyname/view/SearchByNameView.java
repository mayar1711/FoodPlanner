package com.example.foodplanner.ui.searchbyname.view;

import com.example.foodplanner.model.data.Meal;

import java.util.List;

public interface SearchByNameView {
   void displaySearchResults(List<Meal> meals);
   void displayError(String msg);

}
