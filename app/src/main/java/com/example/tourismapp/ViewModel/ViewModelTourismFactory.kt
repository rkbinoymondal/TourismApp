package com.example.tourismapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tourismapp.Repository.TourismRepo

class ViewModelTourismFactory(val repo : TourismRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelTourism(repo) as T;
    }
}