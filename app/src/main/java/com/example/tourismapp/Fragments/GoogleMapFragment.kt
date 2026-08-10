package com.example.tourismapp.Fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tourismapp.R
import com.example.tourismapp.ViewModel.MapSharedViewModel
import com.example.tourismapp.databinding.FragmentAnalysisBinding
import com.example.tourismapp.databinding.FragmentGoogleMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class GoogleMapFragment : Fragment() , OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var _binding : FragmentGoogleMapBinding? = null;
    private val binding get() = _binding!!;

    lateinit var mapSharedViewModel : MapSharedViewModel;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGoogleMapBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapSharedViewModel = ViewModelProvider(requireActivity()).get(MapSharedViewModel::class.java);

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mapSharedViewModel.selectedPlace.observe(viewLifecycleOwner, Observer {
            if (it != null){
                val lat = it.latitude.toDoubleOrNull();
                val lon = it.longitude.toDoubleOrNull();

                if (lat != null && lon != null){
                    val location = LatLng(lat,lon);
                    mMap.clear();

                    mMap.addMarker(MarkerOptions().position(location).title(it.placeName).snippet("${it.city}, ${it.state}"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,13f));

                    mMap.addCircle(CircleOptions().center(location).radius(1000.0).fillColor(Color.argb(50,255,0,255)).strokeWidth(0f))
                }
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null;
    }
}