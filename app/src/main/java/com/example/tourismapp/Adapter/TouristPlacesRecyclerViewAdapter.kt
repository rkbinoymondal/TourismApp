package com.example.tourismapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.tourismapp.Model.TouristPlaces
import com.example.tourismapp.databinding.TourismCardBinding

class TouristPlacesRecyclerViewAdapter(val context : Context, val touristPlacesList : List<TouristPlaces>) : RecyclerView.Adapter<TouristPlacesRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TourismCardBinding.inflate(LayoutInflater.from(context),parent,false);
        return ViewHolder(binding);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val touristPlace = touristPlacesList[position];
        holder.binding.placeName.text = touristPlace.placeName
        holder.binding.locatedIn.text = "Located in : ${touristPlace.city}, ${touristPlace.state}"
        holder.binding.famousFor.text = "Famous for : ${touristPlace.famousFor}"
        holder.binding.desc.text = touristPlace.description
        Glide.with(context).load(touristPlace.imageUrl).into(holder.binding.img);
    }

    override fun getItemCount(): Int {
        return touristPlacesList.size;
    }

    class ViewHolder (val binding : TourismCardBinding) : RecyclerView.ViewHolder(binding.root){

    }
}