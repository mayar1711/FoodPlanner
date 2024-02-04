package com.example.foodplanner.model.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.model.data.Meal;

@Database(entities = {Meal.class},version = 3,exportSchema = false)

public abstract class MyDataBase extends RoomDatabase {
    public static final String DATABASE_NAME="Mdatabase";
    public static MyDataBase database=null;
    public abstract MealDao getproductDao();
    public static synchronized MyDataBase getInstance(Context context){
        if(database==null){
            database= Room.databaseBuilder(context.getApplicationContext(),MyDataBase.class,DATABASE_NAME)
                    .build();
        }
        return database;
    }

}
