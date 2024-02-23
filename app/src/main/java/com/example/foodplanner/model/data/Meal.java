package com.example.foodplanner.model.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (tableName = "meal")
public class Meal implements Serializable {
        @PrimaryKey
        @NonNull
        public String idMeal;
        public String strMeal;

    @NonNull
    public String getIdMeal() {
        return idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    @Ignore
        public Object strDrinkAlternate;

    public void setIdMeal(@NonNull String idMeal) {
        this.idMeal = idMeal;
    }

    public String strCategory;

    public String getStrArea() {
        return strArea;
    }

    public String getStrSource() {
        return strSource;
    }

    public void setStrSource(String strSource) {
        this.strSource = strSource;
    }

    public Object getStrImageSource() {
        return strImageSource;
    }

    public void setStrImageSource(Object strImageSource) {
        this.strImageSource = strImageSource;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String strArea;
        public String strInstructions;

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String strMealThumb;
        @Ignore
        public Object strTags;
        public String strYoutube;
        public String strIngredient1;
        public String strIngredient2;
        public String strIngredient3;
        public String strIngredient4;
        public String strIngredient5;
        public String strIngredient6;
        public String strIngredient7;
        public String strIngredient8;
        public String strIngredient9;
        public String strIngredient10;
        public String strIngredient11;
        public String strIngredient12;
        public String strIngredient13;
        public String strIngredient14;
        public String strIngredient15;
        public String strIngredient16;
        public String strIngredient17;
        public String strIngredient18;
        public String strIngredient19;
        public String strIngredient20;
        public String strMeasure1;
        public String strMeasure2;
        public String strMeasure3;
        public String strMeasure4;
        public String strMeasure5;
        public String strMeasure6;
        public String strMeasure7;
        public String strMeasure8;
        public String strMeasure9;
        public String strMeasure10;
        public String strMeasure11;
        public String strMeasure12;
        public String strMeasure13;
        public String strMeasure14;
        public String strMeasure15;
        public String strMeasure16;
        public String strMeasure17;
        public String strMeasure18;
        public String strMeasure19;
        public String strMeasure20;
        @Ignore
        public String strSource;

        @Ignore
        public Object strImageSource;

        @Ignore
        public Object strCreativeCommonsConfirmed;

        @Ignore
        public Object dateModified;
    }
