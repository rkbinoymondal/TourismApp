package com.example.tourismapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tourismapp.Model.TouristPlaces
import com.example.tourismapp.Repository.Response
import com.example.tourismapp.Repository.TourismRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelTourism(val repo : TourismRepo) : ViewModel() {
    init{
        viewModelScope.launch(Dispatchers.IO){
            repo.getTouristPlaces();
        }
    }
    val touristPlacesList : LiveData<Response<List<TouristPlaces>>> = repo.touristPlacesList;
}