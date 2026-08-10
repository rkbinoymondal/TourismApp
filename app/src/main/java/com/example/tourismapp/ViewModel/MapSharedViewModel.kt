package com.example.tourismapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tourismapp.Model.TouristPlaces

class MapSharedViewModel : ViewModel() {
    private val _selectedPlace = MutableLiveData<TouristPlaces?>()

    val selectedPlace : LiveData<TouristPlaces?> = _selectedPlace;

    fun selectPlace(place : TouristPlaces){
        _selectedPlace.value = place;
    }

    fun clearSelection(){
        _selectedPlace.value = null;
    }
}