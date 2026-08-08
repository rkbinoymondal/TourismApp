package com.example.tourismapp.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TourismAPI {
    private const val BASE_URL = "https://tourism-2-h9ig.onrender.com/"

    fun getAPIInstance() : Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
}