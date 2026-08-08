package com.example.tourismapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tourismapp.Application.MyApplication
import com.example.tourismapp.Repository.Response
import com.example.tourismapp.ViewModel.ViewModelTourism
import com.example.tourismapp.ViewModel.ViewModelTourismFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val repository = (application as MyApplication).repository

        val mainViewModel = ViewModelProvider(this,ViewModelTourismFactory(repository)).get(ViewModelTourism::class.java);

        mainViewModel.touristPlacesList.observe(this,Observer{
            when(it){
                is Response.Loading -> {
                    Log.d("RK","In Loading State")
                }
                is Response.Success -> {
                    for (place in it.data!!){
                        Log.d("RK","${place}")
                    }
                }
                is Response.Failure -> {
                    Log.d("RK",it.errorMessage!!);
                }
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}