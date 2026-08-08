package com.example.tourismapp.API

import com.example.tourismapp.Model.TouristPlaces
import retrofit2.Response
import retrofit2.http.GET

interface TourismService {
    @GET("/tourisms")
    suspend fun getTouristPlacesFromServer() : Response<List<TouristPlaces>>
}