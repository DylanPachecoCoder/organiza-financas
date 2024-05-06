package com.example.organizafinancas.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.organizafinancas.databinding.ItemFilterOptionBinding
import com.example.organizafinancas.domain.model.PaymentFilter

class FilterAdapter(
    private val filterOptions: List<PaymentFilter> = mutableListOf(),
    private val onItemClicked: (PaymentFilter, Boolean) -> Unit
) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemFilterOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = filterOptions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val option = filterOptions[position]
        holder.bind(option, onItemClicked)
    }

    inner class ViewHolder(private val binding: ItemFilterOptionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(option: PaymentFilter, onItemClicked: (PaymentFilter, Boolean) -> Unit) {
            with(binding.checkboxFilterOption) {
                text = option.type.paymentType
                setOnCheckedChangeListener { _, isChecked ->
                    onItemClicked(option, isChecked)
                }
            }
        }
    }
}
