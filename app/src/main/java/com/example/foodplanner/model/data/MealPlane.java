package com.example.foodplanner.model.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "plane")
public class MealPlane implements Serializable {
    @PrimaryKey
    @NonNull
    public String idMeal;
    public String strMeal;
    public String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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
    public void setMealData(Meal meal) {
        this.idMeal = meal.getIdMeal();
        this.strMeal = meal.getStrMeal();
        this.strCategory = meal.getStrCategory();
        this.strArea = meal.getStrArea();
        this.strInstructions = meal.strInstructions;
        this.strMealThumb = meal.getStrMealThumb();
        this.strYoutube = meal.strYoutube;
        this.strIngredient1 = meal.strIngredient1;
        this.strIngredient2 = meal.strIngredient2;
        this.strIngredient3 = meal.strIngredient3;
        this.strIngredient4 = meal.strIngredient4;
        this.strIngredient5 = meal.strIngredient5;
        this.strIngredient6 = meal.strIngredient6;
        this.strIngredient7 = meal.strIngredient7;
        this.strIngredient8 = meal.strIngredient8;
        this.strIngredient9 = meal.strIngredient9;
        this.strIngredient10 = meal.strIngredient10;
        this.strIngredient11 = meal.strIngredient11;
        this.strIngredient12 = meal.strIngredient12;
        this.strIngredient13 = meal.strIngredient13;
        this.strIngredient14 = meal.strIngredient14;
        this.strIngredient15 = meal.strIngredient15;
        this.strIngredient16 = meal.strIngredient16;
        this.strIngredient17 = meal.strIngredient17;
        this.strIngredient18 = meal.strIngredient18;
        this.strIngredient19 = meal.strIngredient19;
        this.strIngredient20 = meal.strIngredient20;
        this.strMeasure1=meal.strMeasure1;
        this.strMeasure2=meal.strMeasure2;
        this.strMeasure3=meal.strMeasure3;
        this.strMeasure4=meal.strMeasure4;
        this.strMeasure5=meal.strMeasure5;
        this.strMeasure6=meal.strMeasure6;
        this.strMeasure7=meal.strMeasure7;
        this.strMeasure8=meal.strMeasure8;
        this.strMeasure9=meal.strMeasure9;
        this.strMeasure10=meal.strMeasure10;
        this.strMeasure11=meal.strMeasure11;
        this.strMeasure12=meal.strMeasure12;
        this.strMeasure13=meal.strMeasure13;
        this.strMeasure14=meal.strMeasure14;
        this.strMeasure15=meal.strMeasure15;
        this.strMeasure16=meal.strMeasure16;
        this.strMeasure17=meal.strMeasure17;
        this.strMeasure18=meal.strMeasure18;
        this.strMeasure19=meal.strMeasure19;
        this.strMeasure20=meal.strMeasure20;

    }
}
