package com.example.organizafinancas.ui.paymentmethod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.organizafinancas.databinding.FragmentPaymentMethodBinding
import com.example.organizafinancas.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentMethodFragment : BaseFragment<FragmentPaymentMethodBinding>() {

    private val viewModel by viewModels<PaymentMethodViewModel>()

    override fun inflateViewBind(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentPaymentMethodBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonNewPaymentMethod.setOnClickListener {
            viewModel.insertPaymentMethod()
        }
    }
}