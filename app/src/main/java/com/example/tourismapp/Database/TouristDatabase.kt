package com.example.tourismapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tourismapp.Model.TouristPlaces

@Database(entities = [TouristPlaces::class], version = 1)
abstract class TouristDatabase : RoomDatabase(){
    abstract fun touristDao() : TouristDao

    companion object{
        @Volatile
        private var INSTANCE : TouristDatabase? = null

        fun getDatabase(context : Context) : TouristDatabase{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context, TouristDatabase::class.java,"touristDb").build();
                }
            }
            return INSTANCE!!;
        }
    }
}