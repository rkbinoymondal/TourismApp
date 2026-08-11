package com.example.tourismapp.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tourismapp.Adapter.OnTouristPlaceClickListener
import com.example.tourismapp.Adapter.TouristPlacesRecyclerViewAdapter
import com.example.tourismapp.Application.MyApplication
import com.example.tourismapp.MainActivity
import com.example.tourismapp.Model.TouristPlaces
import com.example.tourismapp.Repository.Response
import com.example.tourismapp.ViewModel.MapSharedViewModel
import com.example.tourismapp.ViewModel.ViewModelTourism
import com.example.tourismapp.ViewModel.ViewModelTourismFactory
import com.example.tourismapp.databinding.FragmentTourismBinding

class TourismFragment : Fragment() {

    private var _binding : FragmentTourismBinding? = null;
    private val binding get() = _binding!!;

    private val fullList = mutableListOf<TouristPlaces>()
    private val filteredList = mutableListOf<TouristPlaces>()
    private lateinit var adapter : TouristPlacesRecyclerViewAdapter

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

        val sharedPref = requireContext().getSharedPreferences("analysisData", Context.MODE_PRIVATE);

        val editor = sharedPref.edit();

        binding.etSearch.addTextChangedListener {
            updateAdapter(binding.etSearch.text.toString());
        }
        mainViewModel.touristPlacesList.observe(viewLifecycleOwner, Observer{
            when(it){
                is Response.Loading -> {
                    binding.shimmerLayout.visibility = View.VISIBLE;
                    binding.shimmerLayout.startShimmer();
                    binding.searchLayout.visibility = View.GONE;
                    binding.lottieError.visibility = View.GONE;
                    binding.header.visibility = View.GONE;
                    binding.touristList.visibility = View.GONE;
                    binding.lottieEmpty.visibility = View.GONE;
                    binding.emptyText.visibility = View.GONE;
                }
                is Response.Success -> {
                    val ct = sharedPref.getInt("successCt",0)
                    editor.putInt("successCt",ct+1);
                    editor.apply();
                    binding.shimmerLayout.visibility = View.GONE;
                    binding.shimmerLayout.stopShimmer();
                    binding.lottieError.visibility = View.GONE;
                    binding.header.visibility = View.VISIBLE;
                    binding.searchLayout.visibility = View.VISIBLE;
                    binding.touristList.visibility = View.VISIBLE;

                    fullList.clear();
                    filteredList.clear();
                    fullList.addAll(it.data!!);
                    filteredList.addAll(it.data!!);

                    adapter = TouristPlacesRecyclerViewAdapter(requireContext(),filteredList, object : OnTouristPlaceClickListener{
                        override fun onPlaceClick(place: TouristPlaces) {
                            mapSharedViewModel.selectPlace(place);
                            val mainActivity = requireActivity() as MainActivity;
                            mainActivity.binding.viewPagerMain.setCurrentItem(2,true);
                        }

                        override fun onGeminiBtnClick(place: TouristPlaces) {
                            val bottomSheet = BottomSheetFragment(place);
                            bottomSheet.show(childFragmentManager,"BottomSheetFragment");
                        }

                    });
                    binding.touristList.adapter = adapter;
                    binding.touristList.layoutManager = LinearLayoutManager(requireContext(),
                        LinearLayoutManager.VERTICAL,false);
                    updateAdapter(binding.etSearch.text.toString());
                }
                is Response.Failure -> {
                    val ct = sharedPref.getInt("failureCt",0)
                    editor.putInt("failureCt",ct+1);
                    editor.apply();
                    binding.shimmerLayout.visibility = View.GONE;
                    binding.shimmerLayout.stopShimmer();
                    binding.searchLayout.visibility = View.GONE;
                    binding.header.visibility = View.GONE;
                    binding.touristList.visibility = View.GONE;
                    binding.lottieError.visibility = View.VISIBLE;
                    binding.lottieEmpty.visibility = View.GONE;
                    binding.emptyText.visibility = View.GONE;
                    Toast.makeText(requireContext(),it.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun updateAdapter(updatedString : String){

        if (!::adapter.isInitialized || fullList.isEmpty()){
            return;
        }

        if (!updatedString.isEmpty()){
            val result = mutableListOf<TouristPlaces>();
            for (place in fullList){
                if (place.city.contains(updatedString, ignoreCase = true) ||
                    place.state.contains(updatedString, ignoreCase = true) ||
                    place.placeName.contains(updatedString, ignoreCase = true)){
                    result.add(place);
                }
            }
            filteredList.clear();
            filteredList.addAll(result);
            adapter.notifyDataSetChanged();
        }
        else{
            filteredList.clear();
            filteredList.addAll(fullList);
            adapter.notifyDataSetChanged();
        }

        if (filteredList.isEmpty()){
            binding.touristList.visibility = View.GONE;
            binding.lottieError.visibility = View.GONE;
            binding.shimmerLayout.visibility = View.GONE;
            binding.shimmerLayout.stopShimmer();
            binding.lottieEmpty.visibility = View.VISIBLE;
            binding.emptyText.visibility = View.VISIBLE;
        }
        else{
            binding.touristList.visibility = View.VISIBLE;
            binding.lottieError.visibility = View.GONE;
            binding.shimmerLayout.visibility = View.GONE;
            binding.shimmerLayout.stopShimmer();
            binding.lottieEmpty.visibility = View.GONE;
            binding.emptyText.visibility = View.GONE;
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null;
    }
}