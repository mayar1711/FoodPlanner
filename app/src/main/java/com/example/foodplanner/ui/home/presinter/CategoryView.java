package com.example.foodplanner.ui.home.presinter;


import com.example.foodplanner.model.data.Category;

import java.util.List;

public interface CategoryView {
    void showCategories(List<Category> categories);
    void showError(String error);
}
