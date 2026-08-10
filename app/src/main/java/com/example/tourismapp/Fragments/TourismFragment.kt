package com.example.tourismapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tourismapp.Adapter.OnTouristPlaceClickListener
import com.example.tourismapp.Adapter.TouristPlacesRecyclerViewAdapter
import com.example.tourismapp.Application.MyApplication
import com.example.tourismapp.MainActivity
import com.example.tourismapp.Model.TouristPlaces
import com.example.tourismapp.R
import com.example.tourismapp.Repository.Response
import com.example.tourismapp.ViewModel.MapSharedViewModel
import com.example.tourismapp.ViewModel.ViewModelTourism
import com.example.tourismapp.ViewModel.ViewModelTourismFactory
import com.example.tourismapp.databinding.FragmentTourismBinding

class TourismFragment : Fragment() {

    private var _binding : FragmentTourismBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTourismBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repo = (requireActivity().application as MyApplication).repository;

        val mainViewModel = ViewModelProvider(this, ViewModelTourismFactory(repo)).get(
            ViewModelTourism::class.java);

        val mapSharedViewModel = ViewModelProvider(requireActivity()).get(MapSharedViewModel::class.java);

        mainViewModel.touristPlacesList.observe(viewLifecycleOwner, Observer{
            when(it){
                is Response.Loading -> {
//                    Toast.makeText(requireContext(),"Data Loading from Server",Toast.LENGTH_SHORT).show();
                }
                is Response.Success -> {
                    binding.lottieLoading.visibility = View.GONE;
                    binding.touristList.visibility = View.VISIBLE;
                    binding.touristList.adapter = TouristPlacesRecyclerViewAdapter(requireContext(),it.data!!, object : OnTouristPlaceClickListener{
                        override fun onPlaceClick(place: TouristPlaces) {
                            mapSharedViewModel.selectPlace(place);
                            val mainActivity = requireActivity() as MainActivity;
                            mainActivity.binding.viewPagerMain.setCurrentItem(2,true);
                        }

                    });
                    binding.touristList.layoutManager = LinearLayoutManager(requireContext(),
                        LinearLayoutManager.VERTICAL,false);
                }
                is Response.Failure -> {
                    binding.lottieLoading.visibility = View.GONE;
                    binding.lottieError.visibility = View.VISIBLE;
                    Toast.makeText(requireContext(),it.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null;
    }
}