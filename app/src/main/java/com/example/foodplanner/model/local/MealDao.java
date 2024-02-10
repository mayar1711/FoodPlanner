package com.example.foodplanner.model.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.foodplanner.model.data.Meal;
import com.example.foodplanner.model.data.MealPlane;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
@Dao
public interface MealDao {
    @Query("Select * from meal")
    Flowable<List<Meal>> getAllProducts();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertProductToFavorite(Meal meal);
    @Delete
    void deleteProductFromFavorite(Meal meal);
    @Query("Select * from plane")
    Flowable<List<MealPlane>> getAllMealPlane();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertMealToPlane(MealPlane meal);
    @Delete
    void deleteMealFromPlane(MealPlane meal);
    @Query("DELETE FROM meal")
    Completable deleteAllMeals();

    @Query("DELETE FROM plane")
    Completable deleteAllPlanes();
}
