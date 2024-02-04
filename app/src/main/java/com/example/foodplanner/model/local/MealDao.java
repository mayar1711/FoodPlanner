package com.example.foodplanner.model.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.foodplanner.model.data.Meal;
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

}
