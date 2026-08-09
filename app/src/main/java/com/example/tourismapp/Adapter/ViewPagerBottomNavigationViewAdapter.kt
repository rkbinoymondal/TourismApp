package com.example.tourismapp.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tourismapp.Fragments.FavoriteFragment
import com.example.tourismapp.Fragments.FeatureFragment
import com.example.tourismapp.Fragments.HomeFragment
import com.example.tourismapp.Fragments.TourismFragment

class ViewPagerBottomNavigationViewAdapter(framentActivity : FragmentActivity) : FragmentStateAdapter(framentActivity){
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment();
            1 -> TourismFragment();
            2 -> FavoriteFragment();
            3 -> FeatureFragment();
            else -> HomeFragment();
        }
    }

    override fun getItemCount(): Int {
        return 4;
    }
}