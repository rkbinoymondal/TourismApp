package com.example.tourismapp.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tourismapp.Model.TouristPlaces

@Dao
interface TouristDao {

    @Insert
    suspend fun insertTouristPlaces(touristPlaces: List<TouristPlaces>)

    @Query("SELECT * FROM tourism")
    suspend fun getTouristPlaces() : List<TouristPlaces>
}