package com.example.organizafinancas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.organizafinancas.databinding.ItemPurchaseDetailBinding
import com.example.organizafinancas.domain.model.Payment

class PaymentListAdapter(
    private val paymentList: MutableList<Payment> = mutableListOf()
) : RecyclerView.Adapter<PaymentListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemPurchaseDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = paymentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(paymentList[position])
    }

    inner class ViewHolder(private val binding: ItemPurchaseDetailBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(payment: Payment) {
            with(binding){
                textviewPurchaseName.text = payment.name
                textviewPurchaseType.text = payment.type.paymentType
                textviewPurchaseDate.text = payment.date
                textviewPurchaseValue.text = payment.value
            }
        }
    }
}
