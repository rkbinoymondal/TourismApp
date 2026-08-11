package com.example.tourismapp.Fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tourismapp.databinding.FragmentAnalysisBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.DefaultValueFormatter

class AnalysisFragment : Fragment() {

    private var _binding : FragmentAnalysisBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAnalysisBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences("analysisData", Context.MODE_PRIVATE);

        val successCt = sharedPref.getInt("successCt",0);
        val failureCt = sharedPref.getInt("failureCt",0);

        val entries = mutableListOf<PieEntry>();

        if (successCt>0){
            entries.add(PieEntry(successCt.toFloat(),"Success"));
        }
        if (failureCt>0){
            entries.add(PieEntry(failureCt.toFloat(),"Failure"));
        }
        if (entries.isEmpty()){
            binding.pieChart.centerText = "No API Calls Yet"
            binding.pieChart.setNoDataText("No data available to display");
            binding.pieChart.invalidate();
            return;
        }

        val dataSet = PieDataSet(entries, "API Statistics");

        val colorsList = mutableListOf<Int>();
        if (successCt>0) colorsList.add(Color.parseColor("#4CAF50"))
        if (failureCt>0) colorsList.add(Color.parseColor("#F44336"))

        dataSet.colors = colorsList;

        dataSet.valueTextSize = 16f;
        dataSet.valueTextColor = Color.WHITE;
        dataSet.sliceSpace = 1f;

        val pieData = PieData(dataSet);

        pieData.setValueFormatter(DefaultValueFormatter(0))

        binding.pieChart.data = pieData;

        binding.pieChart.description.isEnabled = false;

        val textColors = Color.parseColor("#00ACC1");

        binding.pieChart.legend.textColor = textColors;
        binding.pieChart.setHoleColor(Color.TRANSPARENT)

        binding.pieChart.centerText = "API Metrics"
        binding.pieChart.setCenterTextColor(textColors)
        binding.pieChart.setCenterTextSize(18f)

        binding.pieChart.invalidate();
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null;
    }

}