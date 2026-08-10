package com.example.tourismapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.tourismapp.Adapter.TouristPlacesRecyclerViewAdapter
import com.example.tourismapp.Adapter.ViewPagerBottomNavigationViewAdapter
import com.example.tourismapp.Application.MyApplication
import com.example.tourismapp.Repository.Response
import com.example.tourismapp.ViewModel.ViewModelTourism
import com.example.tourismapp.ViewModel.ViewModelTourismFactory
import com.example.tourismapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPagerMain.adapter = ViewPagerBottomNavigationViewAdapter(this);

        binding.viewPagerMain.isUserInputEnabled = false;

        binding.bottomNavView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.home -> binding.viewPagerMain.setCurrentItem(0,true);
                R.id.tourism -> binding.viewPagerMain.setCurrentItem(1,true);
                R.id.map -> binding.viewPagerMain.setCurrentItem(2,true);
                R.id.chart -> binding.viewPagerMain.setCurrentItem(3,true);
            }
            true
        }

        binding.viewPagerMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bottomNavView.selectedItemId =
                    when(position){
                        0 -> R.id.home
                        1 -> R.id.tourism
                        2 -> R.id.map
                        3 -> R.id.chart
                        else -> R.id.home
                    }
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
    }
}