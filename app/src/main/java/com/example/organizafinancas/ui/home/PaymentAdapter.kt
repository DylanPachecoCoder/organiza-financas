package com.example.organizafinancas.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.organizafinancas.commons.extensions.ONE
import com.example.organizafinancas.commons.extensions.format
import com.example.organizafinancas.commons.extensions.toCurrency
import com.example.organizafinancas.databinding.ItemPaymentDetailBinding
import com.example.organizafinancas.domain.model.Payment

class PaymentAdapter(
    private val paymentList: List<Payment> = mutableListOf()
) : RecyclerView.Adapter<PaymentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemPaymentDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = paymentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val isLast = position == itemCount - Int.ONE
        holder.bind(paymentList[position], isLast)
    }

    inner class ViewHolder(private val binding: ItemPaymentDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(payment: Payment, isLast: Boolean) {
            with(binding) {
                chip.text = payment.category.name
                textviewPaymentName.text = payment.name
                textviewPaymentType.text = payment.type.paymentType
                textviewPaymentValue.text = payment.value.toCurrency()
                textviewPaymentDate.text = payment.date.format()
                divider.isVisible = isLast.not()
            }
        }
    }
}