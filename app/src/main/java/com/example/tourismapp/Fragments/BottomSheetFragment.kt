package com.example.tourismapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.tourismapp.Model.TouristPlaces
import com.example.tourismapp.databinding.BottomSheetBinding
import com.example.tourismapp.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BottomSheetFragment(val place : TouristPlaces) : BottomSheetDialogFragment() {

    private var _binding : BottomSheetBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = BottomSheetBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.placeTitle.text = place.placeName
        binding.subTitle.text = "${place.city} , ${place.state}";

        val generativeModel = GenerativeModel(modelName = "gemini-3-flash-preview", apiKey = BuildConfig.Gemini_API_KEY)

        val prompt = "Give a concise travel guide for ${place.placeName} in ${place.city}. Include best time to visit, key highlights, and famous food in under 120 words. Give me the output by removing the asterisks, double stars and other staffs, give me clean output"

        binding.progressBar.visibility = View.VISIBLE;
        binding.scrollViewContent.visibility = View.GONE

        lifecycleScope.launch(Dispatchers.IO){
            try{
                val response = generativeModel.generateContent(prompt);
                val responseText = response.text ?: "No details available";

                withContext(Dispatchers.Main){
                    binding.progressBar.visibility = View.GONE
                    binding.scrollViewContent.visibility = View.VISIBLE;
                    binding.geminiResponse.text = responseText;
                }
            }
            catch (e : Exception){
                withContext(Dispatchers.Main){
                    binding.progressBar.visibility = View.GONE
                    binding.scrollViewContent.visibility = View.VISIBLE;
                    binding.geminiResponse.text = "Error : ${e.message}";
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null;
    }
}