package com.example.tourismapp.Application

import android.app.Application
import com.example.tourismapp.API.TourismAPI
import com.example.tourismapp.API.TourismService
import com.example.tourismapp.Database.TouristDatabase
import com.example.tourismapp.Repository.TourismRepo

class MyApplication : Application() {

    lateinit var repository : TourismRepo;

    override fun onCreate() {
        super.onCreate()
        initialize();
    }
    fun initialize(){
        val service = TourismAPI.getAPIInstance().create(TourismService::class.java);
        val database = TouristDatabase.getDatabase(applicationContext);
        val dao = database.touristDao();

        repository = TourismRepo(applicationContext,service,dao);
    }
}