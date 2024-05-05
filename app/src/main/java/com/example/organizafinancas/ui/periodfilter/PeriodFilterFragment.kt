package com.example.organizafinancas.ui.periodfilter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.organizafinancas.commons.di.provideDataPicker
import com.example.organizafinancas.databinding.FragmentPeriodFilterBinding
import com.example.organizafinancas.domain.model.Period
import com.example.organizafinancas.ui.adapter.PeriodFilterAdapter

class PeriodFilterFragment : Fragment() {

    private var _binding: FragmentPeriodFilterBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeriodFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setupList()
    }

    private fun setupList() {
        binding.recyclerviewPeriodFilter.adapter = PeriodFilterAdapter(providePeriodList()) {
            val picker = provideDataPicker()
            picker.show(parentFragmentManager, TAG)
        }
    }

    private fun providePeriodList() = listOf(
        Period(),
        Period(),
        Period(),
        Period(),
        Period(),
    )

    companion object{
        private const val TAG = "tag"
    }
}