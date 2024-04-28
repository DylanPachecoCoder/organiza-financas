package com.example.organizafinancas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.organizafinancas.databinding.ItemPurchaseDetailBinding
import com.example.organizafinancas.domain.model.Purchase

class PurchaseListAdapter(
    private val purchaseList: MutableList<Purchase> = mutableListOf()
) : RecyclerView.Adapter<PurchaseListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemPurchaseDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = purchaseList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(purchaseList[position])
    }

    inner class ViewHolder(private val binding: ItemPurchaseDetailBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(purchase: Purchase) {
            with(binding){
                textviewPurchaseName.text = purchase.name
                textviewPurchaseType.text = purchase.type
                textviewPurchaseDate.text = purchase.date
                textviewPurchaseValue.text = purchase.value
            }
        }
    }
}
