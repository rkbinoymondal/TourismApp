package com.example.tourismapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tourismapp.MainActivity
import com.example.tourismapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnExploreNow.setOnClickListener {
            (requireActivity() as MainActivity).binding.viewPagerMain.setCurrentItem(1,true);
        }

        binding.cardFeatureMap.setOnClickListener {
            (requireActivity() as MainActivity).binding.viewPagerMain.setCurrentItem(2,true);
        }

        binding.cardFeatureAnalytics.setOnClickListener {
            (requireActivity() as MainActivity).binding.viewPagerMain.setCurrentItem(3,true);
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null;
    }
}