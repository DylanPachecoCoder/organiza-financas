package com.example.organizafinancas.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.organizafinancas.databinding.FragmentHomeBinding
import com.example.organizafinancas.domain.enums.PaymentTypeEnum
import com.example.organizafinancas.domain.model.Payment
import com.example.organizafinancas.ui.adapter.FilterAdapter
import com.example.organizafinancas.ui.adapter.PaymentListAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.text.observe(viewLifecycleOwner) {}
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLists()
    }

    private fun setupLists() {
        with(binding) {
            recyclerview.adapter = provideFilterAdapter()
            recyclerview2.adapter = providePurchaseAdapter()
        }
    }

    private fun providePurchaseAdapter() = PaymentListAdapter(providePurchaseList())

    private fun providePurchaseList() =
        mutableListOf(
            Payment(type = PaymentTypeEnum.CREDITO),
            Payment(type = PaymentTypeEnum.DEBITO),
            Payment(type = PaymentTypeEnum.PIX),
            Payment(type = PaymentTypeEnum.DINHEIRO),
            Payment(type = PaymentTypeEnum.VR),
            Payment(type = PaymentTypeEnum.VR),
            Payment(type = PaymentTypeEnum.CREDITO),
            Payment(type = PaymentTypeEnum.VA),
            Payment(type = PaymentTypeEnum.VA),
            Payment(type = PaymentTypeEnum.CREDITO),
            Payment(type = PaymentTypeEnum.DEBITO),
            Payment(type = PaymentTypeEnum.DEBITO),
        )

    private fun provideFilterAdapter() = FilterAdapter(provideFilterList())

    private fun provideFilterList() =
        mutableListOf("credito", "debito", "dinheiro", "pix", "vr", "va")

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}