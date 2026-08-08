package com.example.tourismapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tourism")
data class TouristPlaces(
    @PrimaryKey
    val id : Long,
    val placeName : String,
    val city : String,
    val state : String,
    val description : String,
    val famousFor : String,
    val imageUrl : String
)
