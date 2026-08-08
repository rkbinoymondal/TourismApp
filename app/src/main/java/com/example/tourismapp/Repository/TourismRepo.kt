package com.example.tourismapp.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tourismapp.API.TourismService
import com.example.tourismapp.Database.TouristDao
import com.example.tourismapp.Model.TouristPlaces
import com.example.tourismapp.NetworkUtils.InternetCheck

class TourismRepo(val context : Context, val tourismService: TourismService, val touristDao: TouristDao) {
    private val mutableTouristPlacesList = MutableLiveData<Response<List<TouristPlaces>>>()

    val touristPlacesList : LiveData<Response<List<TouristPlaces>>> = mutableTouristPlacesList;

    suspend fun getTouristPlaces(){
        if (InternetCheck.isInternetAvailable(context)){
            mutableTouristPlacesList.postValue(Response.Loading());
            try{
                val tourismList = tourismService.getTouristPlacesFromServer()
                val tourismListData = tourismList.body();
                if (tourismList != null && tourismListData != null){
                    mutableTouristPlacesList.postValue(Response.Success(tourismListData));
                    val touristListDatabase = touristDao.getTouristPlaces();
                    if (touristListDatabase.size == 0){
                        touristDao.insertTouristPlaces(tourismListData);
                    }
                }
                else{
                    mutableTouristPlacesList.postValue(Response.Failure("Something Went Wrong, not able to fetch data from Server"));
                }
            }
            catch (e : Exception){
                mutableTouristPlacesList.postValue(Response.Failure(e.message.toString()))
            }
        }
        else{
            val touristListDatabase = touristDao.getTouristPlaces();
            if (touristListDatabase.size != 0){
                mutableTouristPlacesList.postValue(Response.Success(touristListDatabase));
            }
            else{
                mutableTouristPlacesList.postValue(Response.Failure("No Internet Connection and no data in database"));
            }
        }
    }

}